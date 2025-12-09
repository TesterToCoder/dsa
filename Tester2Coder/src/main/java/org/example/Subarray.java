package org.example;

import java.util.HashMap;
import java.util.Map;

public class Subarray {

    private static int brute_findSubarray(int[] arr, int s) {
        int len = 0;

        for(int i =0;i<arr.length;i++)
        {
            int sum =0;
            for(int j =i;j<arr.length;j++){
                sum = sum+arr[j];
                if(sum ==s){
                  len = Math.max(len,j-i+1);
                }
            }
        }

        return len;
    }

    private static int better_findSubarray(int[] arr, int s) {
        int len = 0;
        Map<Integer , Integer> presum = new HashMap<>();
        int sum =0;
        for(int i =0;i<arr.length;i++)
        {
            sum = sum+arr[i];
            if(sum==s){
                len=Math.max(len,i+1);
            }

            if(presum.containsKey(sum-s)){
                len = Math.max(len, i-presum.get(sum-s));
            }
            if(!presum.containsKey(sum)){
                presum.put(sum,i);
            }
        }

        return len;
    }


    private static int optimal_findSubarray(int[] arr, int s) {

        int right =0;
        int left =0;
        int len =0;
        int sum = arr[0];
        int n = arr.length;

        while(right<n){
            while(sum>s && left<=right){
                sum=sum-arr[left];
                left++;

            }
            if(sum==s){
                len = Math.max(len,right-left+1);
            }
            right++;
            if(right<n){
                sum= sum+arr[right];
            }
        }

        return len;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,1,1,1,1,3,3};
        int s = 6;
        int brute_len = brute_findSubarray(arr, s);
        System.out.println("Max length(brute approach) of sub array with sum ="+s+" is "+brute_len);

        int better_len = better_findSubarray(arr, s);
        System.out.println("Max length(better Approach) of sub array with sum ="+s+" is "+better_len);

        int optimal_len = optimal_findSubarray(arr, s);
        System.out.println("Max length(optimal Approach) of sub array with sum ="+s+" is "+optimal_len);
    }



}
