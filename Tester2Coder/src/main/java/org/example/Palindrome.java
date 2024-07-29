package org.example;


import java.util.Scanner;

class Palindrome {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        String formattedString = input.replaceAll("[^a-zA-Z0-9]","").toLowerCase() ;
        boolean isPalindromeFlag = isPalindrome(formattedString,0,formattedString.length()-1);
        System.out.println("first method - "+isPalindromeFlag);
        boolean isPalin = palindrome(0,formattedString);
        System.out.println("second method - "+isPalin);

    }
    public static boolean isPalindrome(String s,int srtIndex, int length) {

        if(srtIndex==length) return true;
        if(s.charAt(srtIndex)!=s.charAt(length))
            return false;

        return isPalindrome(s.substring(srtIndex+1,length),0,length-2);

    }

    public static boolean palindrome(int i, String s){

        if(i>=s.length()/2) return true;
        if(s.charAt(i)!=s.charAt(s.length()-i-1))
            return false;

        return palindrome(i+1,s);
    }
}