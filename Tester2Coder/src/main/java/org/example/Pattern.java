package org.example;

import java.util.Scanner;

import static java.lang.Math.min;

public class Pattern {

    public static void pattern1(int n){

        for(int i=0;i<n;i++){

            for(int j=0;j<n;j++){

                System.out.print("* ");
            }
            System.out.println();

        }

    }
    public static void pattern2(int n){

        for(int i=0;i<n;i++){

            for(int j=0;j<=i;j++){

                System.out.print("* ");
            }
            System.out.println();

        }

    }
    public static void pattern3(int n){

        for(int i=1;i<=n;i++){

            for(int j=1;j<=i;j++){

                System.out.print(j);
            }
            System.out.println();

        }

    }
    public static void pattern4(int n){

        for(int i=1;i<=n;i++){

            for(int j=1;j<=i;j++){

                System.out.print(i);
            }
            System.out.println();

        }

    }
    public static void pattern5(int n){

        for(int i=0;i<n;i++){

            for(int j=0;j<n-i;j++){

                System.out.print("* ");
            }
            System.out.println();

        }

    }
    public static void pattern6(int n){

        for(int i=0;i<n;i++){

            for(int j=1;j<=n-i;j++){

                System.out.print(j+" ");
            }
            System.out.println();

        }

    }
    public static void pattern7(int n){

        for(int i=1;i<=n;i++){

            for(int j =0;j<n-i;j++){
                System.out.print(" ");
            }
            for(int j =0;j<2*i-1;j++){
                System.out.print("*");
            }
            System.out.println();
        }

    }
    public static void pattern8(int n){

        for(int i=1;i<=n;i++){

            for(int j =0;j<i-1;j++){
                System.out.print(" ");
            }
            for(int j =0;j<2*n-(2*i-1);j++){
                System.out.print("*");
            }
            System.out.println();
        }

    }
    public static void pattern9(int n){

        for(int i=1;i<=n;i++){

            for(int j =0;j<i;j++){
                System.out.print("*");
            }
            System.out.println();
        }
        for(int i=1;i<n;i++){
            for(int j=n;j>i;j--){
                System.out.print("*");
            }
            System.out.println();
        }
    }
    public static void pattern9_2(int n){
        for(int i=1;i<=2*n-1;i++){
            int stars =i;
            if(i>n){
                stars =2*n-i;
            }
            for(int j =0;j<stars;j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
    public static void pattern10(int n){
        int start;
        for(int i=1;i<=n;i++){
           start = 1;
           if(i%2==0){
               start =0;
           }
           for(int j =0;j<i;j++){
               System.out.print(start);
               start = 1-start;
           }
           System.out.println();

        }
    }
public static void pattern11(int n){
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=i;j++){
                System.out.print(j);
            }
            for(int j=0;j<2*n-2*i;j++){
                System.out.print(" ");
            }
            for(int j=i;j>=1;j--){
                System.out.print(j);
            }
            System.out.println();
    }
}
    public static void pattern11_2(int n){
        int space = 2*(n-1);
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=i;j++){
                System.out.print(j);
            }
            for(int j=0;j<space;j++){
                System.out.print(" ");
            }
            for(int j=i;j>=1;j--){
                System.out.print(j);
            }
            System.out.println();
            space -=2;
        }
    }
    public static void pattern12(int n){
        int num =1;
        for(int i=1;i<=n;i++){
            for(int j =0;j<i;j++){
                System.out.print(num+" ");
                num+=1;
            }
            System.out.println();
        }
    }
    public static void pattern13(int n){
        char ch ='A';
        for(int i=1;i<=n;i++){
            for(int j =0;j<i;j++){
                System.out.print(ch+" ");
                ch+=1;
            }
            System.out.println();
        }
    }

    public static void pattern13_2(int n) {
        for(int i=1;i<=n;i++){
            for(char ch='A';ch<'A'+i;ch++){
                System.out.print(ch+" ");
            }
            System.out.println();
        }
    }
    public static void pattern14(int n) {
        for(int i=n;i>0;i--){
            for(char ch='A';ch<'A'+i;ch++){
                System.out.print(ch+" ");
            }
            System.out.println();
        }
    }
    public static void pattern15(int n) {
        char ch ='A';
        for(int i=0;i<n;i++){
            for(int j=0;j<=i;j++){
                System.out.print(ch+" ");
            }
            System.out.println();
            ch+=1;
        }
    }
    public static void pattern15_2(int n) {
        for(int i=0;i<n;i++){
            char ch = (char) ('A'+ i);
            for(int j=0;j<=i;j++){
                System.out.print(ch+" ");
            }
            System.out.println();
        }
    }
    public static void pattern16(int n) {
        for(int i=1;i<=n;i++){
            char ch ='A';
            for(int j=0;j<n-i;j++){
                System.out.print(" ");
            }
            for(int j=0;j<2*i-1;j++){
                System.out.print(ch);
                if(j<(2*i-1)/2){
                ch= (char) (ch+ 1);}
                else ch = (char) (ch-1);
            }
            System.out.println();
        }
    }
    public static void pattern17(int n) {

        for(int i=0;i<n;i++){
            char ch = (char) ('A'+(n-1));
            ch = (char) (ch-i);
            for(int j=0;j<=i;j++){
                System.out.print(ch+" ");
                ch= (char) (ch+1);
            }
            System.out.println();
        }
    }
    public static void pattern17_2(int n) {
        for(int i=0;i<n;i++){
            for(char ch = (char) ('A'+n-1-i); ch<='A'+n-1; ch++){
                System.out.print(ch+" ");
            }
            System.out.println();
        }
    }
    public static void pattern18(int n) {

        for(int i=n;i>0;i--){
            for(int j =0;j<i;j++){
                System.out.print("*");
            }
            for(int j=0;j<2*(n-i);j++)
            {
                System.out.print(" ");
            }
            for(int j =0;j<i;j++){
                System.out.print("*");
            }
            System.out.println();
        }
        for(int i=1;i<=n;i++){
            for(int j=0;j<i;j++){
                System.out.print("*");
            }
            for(int j=0;j<2*(n-i);j++)
            {
                System.out.print(" ");
            }
            for(int j =0;j<i;j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
    public static void pattern19(int n) {
        int space = 2*n-2;
        for(int i=1;i<=2*n-1;i++){
            int stars = i;
            if(i>n) stars =2*n-i;
          for(int j=0;j<stars;j++){
             System.out.print("*");
          }
          for(int j=0;j<space;j++){
                System.out.print(" ");
          }
          for(int j=0;j<stars;j++){
                System.out.print("*");
          }
          System.out.println();
          if(i<n) space -=2;
          else  space +=2;
        }
    }
    public static void pattern20(int n) {
        for(int i=n;i>0;i--){
            if(i==n||i==1){
            for(int j=0;j<n;j++){
                System.out.print("*");
            }
            } else {
                System.out.print("*");
                for(int j =0;j<n-2;j++){
                    System.out.print(" ");
                }
                System.out.print("*");
            }
            System.out.println();
        }
    }
    public static void pattern20_1(int n) {

     for(int i=0;i<n;i++){

         for(int j=0;j<n;j++){
             if(i==0||i==n-1||j==0||j==n-1){
                 System.out.print("*");
             } else System.out.print(" ");

         }
         System.out.println();
     }

    }

    public static void pattern21(int n) {
        for(int i=0;i<2*n-1;i++){
            for(int j =0;j<2*n-1;j++){
                int top = i;
                int right =j;
                int bottom = 2*n-2-i;
                int left = 2*n-2-j;
                System.out.print(n-(min(min(top,right), min(left,bottom))));

            }
                System.out.println();
        }
    }

    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for(int i = 0;i<tc;i++)
        {
            int n = sc.nextInt();
            pattern21(n);

        }
    }
}
