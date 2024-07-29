package org.example;

import java.util.ArrayList;

public class Factorial {


    static ArrayList<Long> al = new ArrayList<>();
    static long fact =1;
    static long i=1;
    static ArrayList<Long> factorialNumbers(long n) {

        fact = fact * i;
        if(fact>n) {
            return al;

        }
        i++;
        al.add(fact);
        return factorialNumbers(n);
    }
//    static ArrayList<Long> factorialNumbers(long n) {
//         ArrayList<Long> al = new ArrayList<>();
//        long fact =1;
//for(long i=1;i<=n;i++) {
//    fact = fact * i;
//    if(fact>=n) break;
//    al.add(fact);
//}
//    return al;
//    }

    public static void main(String[] args) {
        System.out.println(factorialNumbers(2));
    }
}

