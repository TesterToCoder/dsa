package org.example;

import java.util.ArrayList;

public class findFact {
    static ArrayList<Long> al = new ArrayList<>();
    static long fact;
    static int i=1;
    static ArrayList<Long> factorialNumbers(long n) {

        while(i<=n){
            fact = findFactorial(i);
            if(fact<n) {
                al.add(fact);
            }
            i++;
        }
        return al;
    }

    static long findFactorial(long n){

        if(n<1) {
            return 1;
        }
        return n * findFactorial(n-1);
    }
}
