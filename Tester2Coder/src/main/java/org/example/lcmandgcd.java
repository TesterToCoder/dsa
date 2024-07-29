package org.example;

import java.util.Arrays;

public class lcmandgcd {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(lcmAndGcd(14L, 8L)));
    }
    static Long[] lcmAndGcd(Long A , Long B) {
        // code here
        Long gcd;
        Long lcm = (A*B);
        while(A>0&&B>0){
            if(A>B) A=A%B;
            else B=B%A;
        }
        if(A==0)
            gcd =B;
        else gcd =A;

        lcm = lcm/gcd;
        Long[] ln = new Long[2];
        ln[0]=lcm;
        ln[1]=gcd;

        return ln;
    }
}
