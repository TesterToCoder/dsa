package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {12,11,4,8,99,1,0};
        merge_sort(arr,0, arr.length-1);

    }

    private static void merge_sort(int[] arr, int l, int r) {
        if(l==r) return;
        int m = (l+r)/2;
        merge_sort(arr,l,m);
        merge_sort(arr,m+1,r);
        merge(arr,l,m,r);

    }

    private static void merge(int[] arr, int l, int m, int r) {

        {
            // Your code here
            ArrayList<Integer> temp = new ArrayList<Integer>();
            int left = l;
            int right = m+1;

            while(left<=m&&right<=r)
            {
                if(arr[left]<=arr[right]){

                    temp.add(arr[left]);
                    left++;
                }
                else
                {
                    temp.add(arr[right]);
                    right++;
                }

            }

            while(right<=r){

                temp.add(arr[right]);
                right++;
            }

            while(left<=m){

                temp.add(arr[left]);
                left++;
            }

            for(int i=0;i<arr.length;i++){

                arr[i] = temp.get(i);
            }

        }
    }
}
