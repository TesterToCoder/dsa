package org.example;

import java.util.Arrays;

public class MoveZerosToEnd {

    public static void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    public static void moveZeroes(int[] nums) {

        int j = -1;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0){
                j=i;
                break;
            }

        }
        if(j==-1)
            return;

        for(int i=j+1; i<nums.length;i++){

            if(nums[i]!=0){
                swap(nums,i,j);
                j++;
            }

        }

    }

    public static void main(String[] args) {

        int[] arr = {1,2,0,3,4,0,0,4,5,6,7,0,4,0,1,0,0};
        moveZeroes(arr);
        System.out.println(Arrays.toString(arr));
    }


}
