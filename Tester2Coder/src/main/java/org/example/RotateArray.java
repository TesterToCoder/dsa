package org.example;

import java.util.Arrays;

public class RotateArray {

    private static int[] rotateArray(int[] arr, int k) {

        int n = arr.length;
        k = k%n;
        reverseArr(arr,0, k-1);
        reverseArr(arr,k,n-1);
        reverseArr(arr,0,n-1);

        return arr;
    }

    private static void reverseArr(int[] arr, int start, int end) {
        int j = end;
        for(int i=start;i<=j;i++){
            int temp = arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
            j--;
        }
    }


    public static void main(String[] args) {

        int[] arr = {1,2,3,4,5,6,7};
        int k = 3;
        int [] result = rotateArray(arr,k);
        System.out.println(Arrays.toString(result));
    }




}
