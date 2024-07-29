package org.example;

import java.util.Arrays;

public class BubbleSort_Recursion {

    private static int[] bubble_sort(int[] arr, int n) {

        if (n <= 1)
            return arr;

        for (int j = 0; j <= n - 2; j++) {
            if (arr[j] > arr[j + 1]) {
                int temp = arr[j + 1];
                arr[j + 1] = arr[j];
                arr[j] = temp;
            }
        }
        return bubble_sort(arr,n-1);
    }

    public static void main(String[] args) {
        int[] arr = {12,11,4,8,99,1,0};
        int [] result = bubble_sort(arr,arr.length);
        System.out.println(Arrays.toString(result));
    }


}
