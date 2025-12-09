package org.example;
public class NumberOfDigits {


    static int countDigits(int N){
        int count =0;
        while(N>0){
            count++;
            N=N/10;
        }
        return count;
    }
    static int evenlyDivides(int N){
        int count =0;
        int num = N;
        while(N>0){
            int rem = N%10;
            if(rem ==0){
                rem =1;
                count--;
            }
            if(num%rem==0){
                count++;}
            N=N/10;
        }
        return count;
    }
    public static void main(String[] args) {
        int result1 = countDigits(22037);
        int result2 = evenlyDivides(12);
        System.out.println(result1);
        System.out.println(result2);

    }
}
