package org.example;

import java.util.Scanner;

class ReverseArray {
    public static void main(String args[] ) throws Exception {
        int n;
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++)
        {
            arr[i]=sc.nextInt();
        }


        int[] resultArr = reverseArr(0,n-1,arr);
        for(int i=0; i<n; i++)
        {
            System.out.println(resultArr[i]);
        }

    }

    public static int[] reverseArr(int l, int r, int[] arr){
        if(l>=r) return arr;
        swap(l,r,arr);
        reverseArr(l+1,r-1,arr);
        return arr;
    }

    private static void swap(int l, int r, int[] arr) {
        int temp = 0;
        temp = arr[r];
        arr[r]=arr[l];
        arr[l]=temp;

    }


}
