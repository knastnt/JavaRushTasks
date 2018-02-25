package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(args[0]);
        //FileInputStream fis = new FileInputStream("D:/1.txt");
        HashMap<Integer, Integer> list = new HashMap<Integer, Integer>();
        while (fis.available()>0){
            int b = fis.read();
            if (list.containsKey(b)){
                int current = list.get(b).intValue();
                list.replace(b, ++current);
            }else{
                list.put(b, 1);
            }
        }
        fis.close();
        char[] keys = new char[list.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> es : list.entrySet()){
            keys[i] = (char)es.getKey().intValue();
            i++;
        }
        Arrays.sort(keys);
        for(char c : keys){
            System.out.println(c + " " + list.get((int)c).intValue());
        }
    }
}
