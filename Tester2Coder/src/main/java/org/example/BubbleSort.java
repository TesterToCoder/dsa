package org.example;

import java.util.Arrays;

public class BubbleSort {

    private static int[] bubble_sort(int[] arr, int n) {

        for(int i = n-1; i>=1;i--){
            int isSwapped =0;

            for(int j =0;j<=i-1;j++){

                if(arr[j]>arr[j+1]) {
                    int temp = arr[j+1];
                    arr[j+1]= arr[j];
                    arr[j]= temp;
                    isSwapped =1;
                }
            }
            if(isSwapped==0) break;
        }


        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {12,11,4,8,99,1,0};
        int [] result = bubble_sort(arr,arr.length);
        System.out.println(Arrays.toString(result));
    }


}
