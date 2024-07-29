package org.example;

public class isPrime {

    public static void main(String[] args) {
        System.out.println(Prime(11580));
    }
    static int Prime(int N)
    {
        // code here
        boolean flag = false;
        for(int i=2;i<N;i++)
        {
            if(N%i==0)
            {
                flag= true;
                break;

            }

        }
        if(flag)
            return 0;

        return 1;

    }

}
