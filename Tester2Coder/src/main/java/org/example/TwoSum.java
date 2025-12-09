package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    private static boolean findTwoSumBrute(int[] arr, int sum) {
        for(int i =0; i <arr.length;i++){
            for(int j =i+1;j<arr.length;j++)
            {
                if(arr[i]+arr[j]==sum) {
                    System.out.println("Brute - The indexes are :"+i +","+ j);
                    return true;
                }
            }
        }
        return false;
    }

    private static int[] findTwoSumOptimal(int[] arr, int sum) {

        Map<Integer,Integer> map = new HashMap<>();
        int indexFirst=0, indexSecond=0;
        for(int i =0; i <arr.length;i++){
            int k = sum - arr[i];

            if(map.containsKey(k)){
                indexSecond = i;
                indexFirst = map.get(k);

            }
            else map.put(arr[i],i);

        }
        return new int[] {indexFirst,indexSecond};
    }
    private static int[] findTwoSum_TwoPointer(int[] arr, int sum) {
        arr = Arrays.stream(arr).sorted().toArray();
        int i = 0,j=arr.length-1;
        while(i<j){
            if(arr[i]+arr[j]==sum)
                return new int[] {i,j};
            else if (arr[i]+arr[j]>sum){
                j++;

            } else if (arr[i]+arr[j]<sum) {
                i++;
            }

        }

        return new int[] {i,j};
    }
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8};
        int sum = 14;

        //TC O(n2) and SC O(1)
        boolean isPresent_Brute = findTwoSumBrute(arr,sum);
        if(isPresent_Brute)
            System.out.println("Brute - sum is present in the array");
        else
            System.out.println("Brute - sum is not present in the array");
        //TC O(nlogn) and SC O(N)
        int[] isPresent_Optimal = findTwoSumOptimal(arr,sum);
        System.out.println("Optimal - Index where the sum is present = "+Arrays.toString(isPresent_Optimal));

        //TC O(nlogn) and SC O(N)
        int[] isPresent_twoPointer = findTwoSum_TwoPointer(arr,sum);
        System.out.println("TwoPointer - Index where the sum is present = "+Arrays.toString(isPresent_twoPointer));

    }




}
