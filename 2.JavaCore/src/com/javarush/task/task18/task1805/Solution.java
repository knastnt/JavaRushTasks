package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.SortedSet;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        String fileName = brdr.readLine();
        brdr.close();
        FileInputStream fis = new FileInputStream(fileName);
        HashSet<Integer> hs = new HashSet<Integer>();
        while(fis.available()>0){
            int readed = fis.read();
            if (!hs.contains(readed)){
                hs.add(readed);
            }
        }
        Integer[] ff = null;
        //ff = (Integer[])hs.toArray();
        ff = hs.toArray(new Integer[hs.size()]);
        Arrays.sort(ff);
        fis.close();
        for(int i : ff){
            System.out.print(i + " ");
        }
    }
}
