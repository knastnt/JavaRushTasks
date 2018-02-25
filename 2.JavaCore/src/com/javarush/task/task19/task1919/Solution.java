package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class Solution {
    public static void main(String[] args) throws IOException {
        String filename = args[0];
        //String filename = "D:/1.txt";
        BufferedReader brdr = new BufferedReader(new FileReader(filename));
        Object[] res = brdr.lines().toArray();
        brdr.close();
        HashMap<String, Double> map = new HashMap<String, Double>();
        for(Object o : res){

            String[] splitted = ((String) o).split(" ");

            if(map.containsKey(splitted[0])){
                //map.get(splitted[0]).compareTo(map.get(splitted[0]).doubleValue() + Double.parseDouble(splitted[1]));
                map.put(splitted[0], map.get(splitted[0]).doubleValue() + Double.parseDouble(splitted[1]));
            }else{
                map.put(splitted[0], Double.parseDouble(splitted[1]));
            }

        }
        Object[] names = map.keySet().toArray();
        Arrays.sort(names);
        for (Object n : names){
            System.out.println(n + " " + map.get(n).doubleValue());
        }

    }
}
