package org.example;

public class reverseNumber {

    public static void main(String[] args) {
      int result = reverse(1534236469);
        System.out.println(result);
    }
    public static int reverse(int x) {
        long revNum =0;
        boolean flag = false;
        if(x<0) {
            x= -x;
            flag = true;
        }
        while(x>0){
            int lastDigit = x%10;
            revNum=(revNum*10) + lastDigit;
            x=x/10;
        }
        if(flag){
            revNum= -revNum;
        }
        if(revNum>Integer.MAX_VALUE||revNum< Integer.MIN_VALUE){
            return 0;
        } else{
            return (int) revNum;
        }

    }
}
