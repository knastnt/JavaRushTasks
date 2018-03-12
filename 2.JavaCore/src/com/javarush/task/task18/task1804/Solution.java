package com.javarush.task.task18.task1804;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Самые редкие байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream(new BufferedReader(new InputStreamReader(System.in)).readLine());
        HashMap<Integer, Integer> mass = new HashMap<Integer, Integer>();
        while (fis.available()>0){
            int readedByte = fis.read();
            if (mass.containsKey(readedByte)){
                mass.replace(readedByte, mass.get(readedByte).intValue() + 1);
            }else{
                mass.put(readedByte, 1);
            }
        }
        fis.close();
        int minCount = getMinvalue(mass);
        if(mass.size() != 0) {
            for (Map.Entry<Integer, Integer> ent : mass.entrySet()) {
                if(minCount == ent.getValue()){
                    System.out.print(ent.getKey() + " ");
                }
            }
        }
    }


    private static int getMinvalue(HashMap<Integer, Integer> mass){
        int minCount = -1;
        if(mass.size() != 0) {
            for (Map.Entry<Integer, Integer> ent : mass.entrySet()) {
                if(minCount == -1 || minCount > ent.getValue()){
                    minCount = ent.getValue();
                }
            }
        }
        return minCount;
    }
}
