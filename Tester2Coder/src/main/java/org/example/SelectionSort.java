package org.example;

import java.util.Arrays;

public class SelectionSort {

    private static int[] selection_sort(int[] arr, int length) {

        for(int i =0; i<=length-2;i++){
            int minIndex = i;

            for(int j =i;j<=length-1;j++){
                if(arr[j]<arr[minIndex]){
                    minIndex =j;
                }
            }

            int temp = arr[minIndex];
            arr[minIndex]= arr[i];
            arr[i]=temp;


        }
        return arr;
    }

    public static void main(String[] args) {

        int[] arr = {12,11,4,8,99,1,0};
        int [] result = selection_sort(arr,arr.length);
        System.out.println(Arrays.toString(result));
    }


}
