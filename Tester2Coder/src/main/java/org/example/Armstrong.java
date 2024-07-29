package org.example;

public class Armstrong {

    public static void main(String[] args) {
        isArmstrong(1634);
    }
    public static void isArmstrong(int num) {
        // wrtie your code here;
        int asnum = 0;
        int lastDigit = 0;
        int digits = 0;
        int temp = num;
        int temp1 = num;
        while (temp1 != 0) {
            digits++;
            temp1 = temp1 / 10;

        }
        while (num > 0) {
            lastDigit = num % 10;
            asnum = (int) (asnum + Math.pow(lastDigit, digits));
            num = num / 10;

        }
        System.out.println("*********"+asnum + "    " +num+"   ");
    }
}
