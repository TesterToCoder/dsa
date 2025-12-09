package org.example;


import okhttp3.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class EmailReaderUtil {

    private static final Logger logger = LoggerFactory.getLogger(EmailReaderUtil.class);

    private static final String TOKEN_URL = "https://login.microsoftonline.com/{tenant-id}/oauth2/v2.0/token";
    private static final String GRAPH_API_URL = "https://graph.microsoft.com/v1.0/users/{email}/messages";
    private static final int TIMEOUT_SECONDS = 300;

    private String clientId;
    private String clientSecret;
    private String tenantId;
    private String userEmail;
    private String accessToken;
    private long tokenExpiryTime = 0;

    public EmailReaderUtil() {
        loadConfigFromProperties();
    }

    private void loadConfigFromProperties() {
        Properties prop = new Properties();
        try {
            FileInputStream inStream = new FileInputStream("resources/email.properties");
            prop.load(inStream);

            this.clientId = prop.getProperty("client_id");
            this.clientSecret = prop.getProperty("client_secret");
            this.tenantId = prop.getProperty("tenant_id");
            this.userEmail = prop.getProperty("email");

            logger.info("Loaded properties successfully.");
        } catch (IOException ex) {
            logger.error("Unable to load email.properties", ex);
        }
    }

    // Reuse the token until it expires
    private String getAccessToken() throws IOException {
        if (System.currentTimeMillis() < tokenExpiryTime && accessToken != null) {
            logger.info("Reusing existing access token.");
            return accessToken;
        }

        OkHttpClient client = new OkHttpClient.Builder().retryOnConnectionFailure(true).build();
        String tokenUrl = TOKEN_URL.replace("{tenant-id}", tenantId);

        Map<String, String> bodyParams = new HashMap<>();
        bodyParams.put("client_id", clientId);
        bodyParams.put("scope", "https://graph.microsoft.com/.default");
        bodyParams.put("client_secret", clientSecret);
        bodyParams.put("grant_type", "client_credentials");

        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        bodyParams.forEach(formBodyBuilder::add);

        RequestBody body = formBodyBuilder.build();
        Request request = new Request.Builder()
                .url(tokenUrl)
                .post(body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                handleErrorResponse(response);
                throw new IOException("Token request failed with code: " + response.code());
            }

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> jsonResponse = objectMapper.readValue(response.body().string(), Map.class);
            accessToken = (String) jsonResponse.get("access_token");

            // Set the token expiry time to current time + 1 hour (3600 seconds)
            tokenExpiryTime = System.currentTimeMillis() + (3600 * 1000);
            logger.info("New access token obtained and valid for 1 hour.");

            return accessToken;
        } catch (SocketTimeoutException e) {
            logger.error("Token request timed out.", e);
            throw new IOException("Token request timed out.", e);
        }
    }

    // Method to handle HTTP error responses
    private void handleErrorResponse(Response response) {
        if (response.code() == 429) { // 429 Too Many Requests (rate limit)
            logger.warn("Rate limit hit. Please retry after some time.");
        } else if (response.code() == 401 || response.code() == 403) { // Unauthorized or Forbidden
            logger.error("Authentication failed. Invalid credentials or token.");
        } else {
            logger.error("Unexpected error: " + response.message());
        }
    }

    public HashMap<String, String> getEmailContent(String subjectSubstring) throws IOException, InterruptedException {
        HashMap<String, String> emailContentMap = new HashMap<>();
        CountDownLatch latch = new CountDownLatch(1);

        String accessToken = getAccessToken();
        OkHttpClient client = new OkHttpClient();

        String apiUrl = GRAPH_API_URL.replace("{email}", userEmail);
        Request request = new Request.Builder()
                .url(apiUrl)
                .get()
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                handleErrorResponse(response);
                throw new IOException("Email fetch failed with code: " + response.code());
            }

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> emailsResponse = objectMapper.readValue(response.body().string(), Map.class);
            boolean foundEmail = false;

            for (Map<String, Object> message : (Iterable<Map<String, Object>>) emailsResponse.get("value")) {
                String subject = (String) message.get("subject");
                if (subject != null && subject.contains(subjectSubstring)) {
                    String body = (String) ((Map<String, Object>) message.get("body")).get("content");
                    emailContentMap.put(subject, body);
                    foundEmail = true;
                    break;
                }
            }

            if (!foundEmail) {
                logger.info("No matching email found. Waiting for a new email...");
                boolean emailReceived = latch.await(TIMEOUT_SECONDS, TimeUnit.SECONDS);

                if (emailReceived) {
                    logger.info("New email received.");
                    return getEmailContent(subjectSubstring);
                } else {
                    logger.info("Timed out waiting for a new email.");
                    emailContentMap.put("No messages found", "No messages found with the subject containing: " + subjectSubstring);
                }
            }
        } catch (SocketTimeoutException e) {
            logger.error("Email fetch request timed out.", e);
            throw new IOException("Email fetch request timed out.", e);
        }

        return emailContentMap;
    }

    // The rest of your email processing logic remains the same

    public static String getPlainTextFromHtmlContent(Map<String, String> emailContentMap) {
        StringBuilder plainTextContent = new StringBuilder();
        for (String htmlContent : emailContentMap.values()) {
            plainTextContent.append(extractFormattedTextFromHtml(htmlContent)).append("\n");
        }
        return removeEmptyLines(plainTextContent.toString());
    }

    private static String extractFormattedTextFromHtml(String html) {
        Document doc = Jsoup.parse(html);
        doc.outputSettings().prettyPrint(false);

        doc.select("br").append("\\n");
        doc.select("p").prepend("\\n\\n");
        doc.select("div").prepend("\\n");
        doc.select("tr").prepend("\\n");
        doc.select("td").append("\\t");

        String text = doc.wholeText().replace("\\n", "\n").replace("\\t", "\t");
        return text;
    }

    private static String removeEmptyLines(String content) {
        String[] lines = content.split("\n");
        StringBuilder result = new StringBuilder();
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                result.append(line).append("\n");
            }
        }
        return result.toString().trim();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        EmailReaderUtil emailReader = new EmailReaderUtil();

        String subjectToSearch = "Reservation Number - 28383939";

        // Fetch emails
        HashMap<String, String> emailContent = emailReader.getEmailContent(subjectToSearch);
        String plainTextContent = getPlainTextFromHtmlContent(emailContent);

        System.out.println("Plain text email content: \n" + plainTextContent);
    }
}

