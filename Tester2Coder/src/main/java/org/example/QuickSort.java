package org.example;

import java.util.Arrays;

public class QuickSort {


    //Function to sort an array using quick sort algorithm.
    static void quickSort(int[] arr, int low, int high)
    {
        // code here
        if(low<high){

            int pIndex = partition(arr,low,high);
            quickSort(arr, low, pIndex-1);
            quickSort(arr, pIndex+1, high);

        }
    }
    static int partition(int arr[], int low, int high)
    {
        // your code here
        int pivot = arr[low];
        int i = low;
        int j = high;

        while(i<j){
            while(arr[i]<=pivot && i<=high-1 )
            {
                i++;
            }

            while(arr[j]>pivot && j>=low+1 )
            {
                j--;
            }

            if(i<j) swap(arr,i,j);

        }
        swap(arr,low,j);
        return j;

    }

    public static void swap (int arr[], int num1, int num2){

        int temp;
        temp = arr[num1];
        arr[num1] = arr[num2];
        arr[num2] = temp;

    }
    
    public static void main(String[] args) {

        int[] arr = {-55,88,53,39,33,10};
        quickSort(arr,0, arr.length-1);
        System.out.println(Arrays.toString(arr));

    }
}
