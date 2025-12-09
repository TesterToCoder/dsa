package org.example;

public class HighestInArray {

    private static int findHighestNum(int[] nums) {

        int highest = nums[0];
        for(int i =1;i<nums.length;i++){
            if(nums[i]>highest){
                highest = nums[i];
            }
        }
        return highest;
    }

    private static int findSecondHighestNum(int[] nums) {
        int highest,secondHighest;
        if(nums[0]>nums[1]){
            highest = nums[0];
            secondHighest = nums[1];
        } else {
            secondHighest = nums[0];
            highest = nums[1];
        }

        for(int i =2;i<nums.length;i++){
            if(nums[i]>highest){
                highest = nums[i];
            } else if  (nums[i]>secondHighest) {
                secondHighest = nums[i];
            }

        }
        return secondHighest;
    }

    public static void main(String[] args){
        int[] nums = {2000,1800,1040,999,50,48,2,7,8,1,4,9,23,11,32,44,23,27,888};
        int highestNum = findHighestNum(nums);
        int secondHighestNum = findSecondHighestNum(nums);

        System.out.println("Highest Number :"+ highestNum);
        System.out.println("Second Highest Number :"+ secondHighestNum);


    }


}
