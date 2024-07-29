package org.example;

import com.sun.mail.imap.IMAPFolder;
import javax.mail.*;
import javax.mail.event.MessageCountAdapter;
import javax.mail.event.MessageCountEvent;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.SearchTerm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class EmailReader1 {

    private static final String HOST = "outlook.office365.com";
    private static final String USERNAME = "dummytest12032006@outlook.com"; // replace with your email address
    private static final String PASSWORD = "DummyTest@12032006##"; // replace with your password
    private static final int TIMEOUT_SECONDS = 300; // Timeout period in seconds (5 minutes)

    public static HashMap<String, String> getEmailContent(String subjectSubstring) {
        HashMap<String, String> emailContentMap = new HashMap<>();

        // Set properties
        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imap.host", HOST);
        properties.put("mail.imap.port", "993");
        properties.put("mail.imap.ssl.enable", "true");

        // Get the Session object
        Session session = Session.getInstance(properties);

        try {
            // Create the IMAP store object and connect with the IMAP server
            Store store = session.getStore("imaps");
            store.connect(HOST, USERNAME, PASSWORD);

            // Create the folder object and open it in read-only mode
            IMAPFolder inbox = (IMAPFolder) store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            // Search for messages with the specified subject substring
            Message[] messages = searchMessagesBySubjectSubstring(inbox, subjectSubstring);

            if (messages.length > 0) {
                for (Message message : messages) {
                    emailContentMap.put(message.getSubject(), getMessageContent(message));
                }
            } else {
                // If no messages are found, wait for new messages
                CountDownLatch latch = new CountDownLatch(1);
                inbox.addMessageCountListener(new MessageCountAdapter() {
                    @Override
                    public void messagesAdded(MessageCountEvent event) {
                        try {
                            for (Message message : event.getMessages()) {
                                if (message.getSubject() != null && message.getSubject().contains(subjectSubstring)) {
                                    emailContentMap.put(message.getSubject(), getMessageContent(message));
                                    latch.countDown(); // Signal that the email has been received
                                    break; // Stop checking after the first match
                                }
                            }
                        } catch (MessagingException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                // Start IDLE command to wait for new messages
                Thread idleThread = new Thread(() -> {
                    try {
                        while (!Thread.currentThread().isInterrupted()) {
                            if (store.isConnected()) {
                                inbox.idle();
                            }
                        }
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                });
                idleThread.start();

                // Wait for the email to arrive or timeout
                if (!latch.await(TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
                    emailContentMap.put("Timeout", "No messages found with the subject containing: " + subjectSubstring + " within the timeout period.");
                }

                // Stop the IDLE thread after the wait
                idleThread.interrupt();
            }

            // Close the store and folder objects
            inbox.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
            emailContentMap.put("Error", e.getMessage());
        }

        return emailContentMap;
    }

    public static String getPlainTextFromHtmlContent(Map<String, String> emailContentMap) {
        StringBuilder plainTextContent = new StringBuilder();
        for (String htmlContent : emailContentMap.values()) {
            plainTextContent.append(extractFormattedTextFromHtml(htmlContent)).append("\n");
        }
        return removeEmptyLines(plainTextContent.toString());
    }

    public static HashMap<String, String> getEmbeddedUrls(Map<String, String> emailContentMap) {
        HashMap<String, String> linksMap = new HashMap<>();
        for (String htmlContent : emailContentMap.values()) {
            linksMap.putAll(extractLinksFromContent(htmlContent));
        }
        return linksMap;
    }

    // Method to search messages by subject substring
    private static Message[] searchMessagesBySubjectSubstring(Folder folder, String subjectSubstring) throws MessagingException {
        SearchTerm searchCondition = new SearchTerm() {
            @Override
            public boolean match(Message message) {
                try {
                    if (message.getSubject() != null && message.getSubject().contains(subjectSubstring)) {
                        return true;
                    }
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                return false;
            }
        };
        return folder.search(searchCondition);
    }

    // Method to extract the content of the message
    private static String getMessageContent(Message message) throws MessagingException, IOException {
        if (message.isMimeType("text/plain")) {
            return message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            return getTextFromMimeMultipart((MimeMultipart) message.getContent());
        }
        return "";
    }

    // Method to extract text from MimeMultipart
    private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
        StringBuilder result = new StringBuilder();
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result.append(bodyPart.getContent().toString());
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result.append(html); // Append the HTML content
            }
        }
        return result.toString();
    }

    // Method to extract formatted plain text from HTML content
    private static String extractFormattedTextFromHtml(String html) {
        Document doc = Jsoup.parse(html);
        doc.outputSettings().prettyPrint(false);

        // Handle block elements that should start on a new line
        doc.select("br").append("\\n");
        doc.select("p").prepend("\\n\\n");
        doc.select("div").prepend("\\n");
        doc.select("tr").prepend("\\n");
        doc.select("td").append("\\t");

        // Replace multiple new lines and tabs with single ones
        String text = doc.wholeText().replace("\\n", "\n").replace("\\t", "\t");
        return text;
    }

    // Method to remove empty lines from the email content
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

    // Method to extract links and their texts from content
    private static HashMap<String, String> extractLinksFromContent(String content) {
        HashMap<String, String> linksMap = new HashMap<>();
        Document doc = Jsoup.parse(content);
        Elements elements = doc.select("a[href]");
        for (Element element : elements) {
            linksMap.put(element.text(), element.attr("href"));
        }
        return linksMap;
    }
}
