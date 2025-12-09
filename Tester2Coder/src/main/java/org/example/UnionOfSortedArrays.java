package org.example;

import java.util.*;

public class UnionOfSortedArrays {


    private static List<Integer> unionArray_Brute(int[] arr1, int[] arr2) {

        Set<Integer> s = new TreeSet<>();
        for (int value : arr1) {
            s.add(value);
        }

        for (int value : arr2) {
            s.add(value);
        }

        ArrayList<Integer> unionArr = new ArrayList<Integer>();
        Iterator<Integer> it = s.iterator();
        int k =0;
        while(it.hasNext()){
            unionArr.add(k, it.next());
            k++;
        }

        return unionArr;
    }

    private static List<Integer> unionArray_Optimal(int[] arr1, int[] arr2) {

        int n1 = arr1.length;
        int n2 = arr2.length;
        int i =0,j = 0;
        List<Integer> list = new ArrayList<>();

        while(i<n1 && j<n2){
            if(arr1[i]<=arr2[j]) {
                if (list.isEmpty() || list.get(list.size()-1)!=(arr1[i])) {
                    list.add(arr1[i]);

                }
                i++;
            }else {
                if (list.isEmpty() || list.get(list.size()-1)!=(arr2[j])) {
                list.add(arr2[j]);

            }
                j++;
            }
        }

        while(i<n1){

                if (list.get(list.size()-1)!=arr1[i]) {
                    list.add(arr1[i]);
                }
                i++;
            }
        while(j<n2){

            if (list.get(list.size()-1)!=arr2[j]) {
                list.add(arr2[j]);
            }
            j++;
        }


        return list;
    }



    public static void main(String[] args) {

        int[] arr1 = {1,1,2,3,4,5};
        int[] arr2 = {2,3,4,4,5,6};
        List<Integer> result_Brute = unionArray_Brute(arr1,arr2);
        List<Integer> result_Optimal = unionArray_Optimal(arr1,arr2);
        System.out.println(result_Brute);
        System.out.println(result_Optimal);
    }




}
