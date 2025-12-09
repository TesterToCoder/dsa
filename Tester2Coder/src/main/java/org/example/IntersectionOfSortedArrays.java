package org.example;

import java.util.ArrayList;
import java.util.List;

public class IntersectionOfSortedArrays {


    private static List<Integer> intersectionArray_Optimal(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;
        int i =0;
        int j =0;

        ArrayList<Integer> result = new ArrayList<>();
        while(i<n1 && j<n2){

            if(arr1[i]<arr2[j]){
                i++;
            } else if (arr2[j]<arr1[i]) {
                j++;

            } else {
                result.add(arr1[i]);
                i++;
                j++;
            }
        }

        return result;
    }

    private static List<Integer> intersectionArray_Brute(int[] arr1, int[] arr2) {

        int n1 = arr1.length;
        int n2 = arr2.length;
        int[] visited = new int[n2];
        ArrayList<Integer> result = new ArrayList<>();
        for (int k =0;k<n1;k++) {
            for (int j = 0; j < n2; j++) {
                if (arr1[k] == arr2[j] && visited[j] == 0) {
                    result.add(arr1[k]);
                    visited[j] = 1;
                    break;

                }
                if(arr2[j]>arr1[k])
                    break;
            }
        }
        return result;
    }


    public static void main(String[] args) {

        int[] arr1 = {1,2,2,2,3,4};
        int[] arr2 = {2,2,3,3};
        List<Integer> result_Brute = intersectionArray_Brute(arr1,arr2);
        List<Integer> result_Optimal = intersectionArray_Optimal(arr1,arr2);
        System.out.println("result_Brute"+result_Brute);
        System.out.println("result_Optimal"+result_Optimal);
    }


}
