package org.example;
import java.util.Arrays;
public class FindIntegerFrequency {

    public static void main(String[] args) {

        int[] para = {8,9};
        Solution.frequencyCount(para,2,9);
    }
}

class Solution{
    public static void frequencyCount(int[] arr, int N, int P)
    {
        int[] arr1 = new int[N+1];
        for (int j : arr) {
            if(j<=N)
            {
                arr1[j]++;
            }

        }
        System.out.print(Arrays.toString(arr1));

        if (N >= 1) System.arraycopy(arr1, 1, arr, 0, N);
        System.out.print(Arrays.toString(arr));
    }
}