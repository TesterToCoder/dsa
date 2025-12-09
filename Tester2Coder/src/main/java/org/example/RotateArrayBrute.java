package org.example;
import java.util.Arrays;

public class RotateArrayBrute {
    private static int[] rotateArrayByOnePlace(int[] arr) {

        int temp = arr[0];
        int length = arr.length;
        int k =1;
        for(int i=0;i<length-1;i++){
            arr[i]=arr[k];
            k++;
        }
        arr[length-1]=temp;

        return arr;
    }
    private static int[] rotateArrayByTwoPlace(int[] arr) {
        int[] temp = new int[2];
        for(int i =0;i<2;i++){
            temp[i]=arr[i];
        }
        //System.out.println("temp array "+Arrays.toString(temp));
        int k =0;
        for(int i=2;i<arr.length;i++){
          arr[k]=arr[i];
          k++;
        }
        //System.out.println("new array "+Arrays.toString(arr));
        int h=0;
        for(int i = arr.length-2;i<=arr.length-1;i++){
            arr[i]=temp[h];
            h++;
        }
        //System.out.println("new2 array "+Arrays.toString(arr));
        return arr;
    }

    public static void main(String[] args) {

        int[] arr = {1,2,3,4,5,6,7};
        int [] result1 = rotateArrayByOnePlace(arr);
        System.out.println("Rotate array by 1 place - "+Arrays.toString(result1));

        int[] arr1 = {1,2,3,4,5,6,7};
        int [] result2 = rotateArrayByTwoPlace(arr1);
        System.out.println("Rotate array by 2 place - "+Arrays.toString(result2));

    }

}
