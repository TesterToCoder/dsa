package org.example;

import java.util.HashMap;
import java.util.Map;

public class CountDuplicateCharactersInString {


    public static void duplicates(String string){
        int count =0;
        HashMap<Character, Integer> mapp = new HashMap<>();
        for(char ch : string.toCharArray())
        {
            mapp.put(ch, mapp.getOrDefault(ch,0)+1);
        }

        for(Map.Entry<Character,Integer> entry : mapp.entrySet()){

            if(entry.getValue()>1){
                System.out.println(entry.getKey() +" : "+entry.getValue());
            }
        }


    }


    public static void main(String [] args){

        String input = "blackmamba";
        duplicates(input);



    }
}
