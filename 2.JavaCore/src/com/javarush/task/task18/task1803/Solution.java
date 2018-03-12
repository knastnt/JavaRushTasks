package com.javarush.task.task18.task1803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Самые частые байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream(new BufferedReader(new InputStreamReader(System.in)).readLine());
        HashMap<Integer, Integer> mass = new HashMap<Integer, Integer>();
        int maxCount = 0;
        while (fis.available()>0){
            int readedByte = fis.read();
            int count;
            if(mass.containsKey(readedByte)){
                count = mass.get(readedByte).intValue() + 1;
                mass.replace(readedByte, count);
            }else{
                count = 1;
                mass.put(readedByte,count);
            }
            if(maxCount<count){
                maxCount = count;
            }
        }
        fis.close();
        for(Map.Entry<Integer, Integer> ent : mass.entrySet()){
            if(ent.getValue() == maxCount){
                System.out.print(ent.getKey() + " ");
            }
        }

    }
}
