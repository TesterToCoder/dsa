package org.example;

import java.util.HashMap;
import java.util.Map;

public class FindSingleNumber {


    private static int findSingleNumber(int[] arr) {

        HashMap<Integer,Integer> frequency = new HashMap<Integer,Integer>();
        for (int j : arr) {

            frequency.put(j, frequency.getOrDefault(j, 0) + 1);

        }
        int result=0;
        for(Map.Entry<Integer,Integer> entry : frequency.entrySet()){

            if(entry.getValue()==1){
                result= entry.getKey();
                break;

            }

        }
        return result;
    }


    private static int findSingleNumberOnlyPositive(int[] arr1) {
            int largest = arr1[0];
            int lenght = arr1.length;
            for(int i =0; i<lenght;i++){
                if(arr1[i]>largest){
                    largest = arr1[i];
                }

            }
            int[] hash = new int[largest+1];
            for(int k =0;k<lenght;k++){
                hash[arr1[k]]++;

            }
            int number =0;
            for(int j =0; j< hash.length;j++){
                if(hash[j]==1){
                    number=j;
                    break;
                }
            }

        return number;
    }
    private static int findSingleNumberWorst(int[] arr1) {

        int num;
        for (int i : arr1) {
            num = i;
            int count =0;
            for(int j : arr1){
                if(j==num)
                    count++;
            }
            if(count ==1) return num;
        }
        return 0;
    }

    private static int findSingleNumberXOR(int[] arr1) {

        int xor = 0;
        for (int i : arr1) {
            xor = xor ^ i;

        }
        return xor;
    }

    public static void main(String[] args) {

        int[] arr1 = {1,2,1,2,4};

        int result_Brute = findSingleNumberOnlyPositive(arr1);
        int result_Optimal = findSingleNumber(arr1);
        System.out.println("result_Optimal "+result_Optimal);
        System.out.println("number which occurs only once is : " +result_Brute);



        int result_xor = findSingleNumberXOR(arr1);
        System.out.println("result_XOR "+result_xor);


    }

}
