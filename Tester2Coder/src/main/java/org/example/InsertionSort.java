package org.example;

import java.util.Arrays;

public class InsertionSort {



    private static int[] insertion_sort(int[] arr, int n) {

        for(int i=0;i<=n-1;i++){

            for(int j=i;j>0;j--){
                if(arr[j-1]>arr[j]){
                    int temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                }

            }
        }


        return arr;
    }

    public static void main(String[] args) {

        int[] arr = {12,11,4,8,99,1,0};
        int [] result = insertion_sort(arr,arr.length);
        System.out.println(Arrays.toString(result));

    }

}
