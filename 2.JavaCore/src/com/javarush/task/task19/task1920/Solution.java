package com.javarush.task.task19.task1920;

/* 
Самый богатый
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        //String fileName = "D:/1.txt";
        BufferedReader brdr = new BufferedReader(new FileReader(fileName));
        Object[] fileContentArr = brdr.lines().toArray();
        brdr.close();

        HashMap<String, Double> resMap = new HashMap<String, Double>();
        Double maxZp = 0.0;

        for(Object o : fileContentArr){
            String[] splitted = ((String)o).split(" ");

            if(resMap.containsKey(splitted[0])){
                resMap.put(splitted[0], resMap.get(splitted[0]).doubleValue() + Double.parseDouble(splitted[1]));
            }else{
                resMap.put(splitted[0], Double.parseDouble(splitted[1]));
            }

            if(resMap.get(splitted[0]).doubleValue()>maxZp){
                maxZp = resMap.get(splitted[0]).doubleValue();
            }
        }

        String[] resultArr = new String[resMap.size()];

        int i=0;
        for (Map.Entry<String, Double> o : resMap.entrySet()){
            if (o.getValue().compareTo(maxZp) == 0){
                resultArr[i] = o.getKey();

            }else{
                resultArr[i] = "";
            }
            i++;
        }


        Arrays.sort(resultArr);
        String result = "";
        for(i=0; i<resultArr.length; i++){
            result = result + resultArr[i] + " ";
        }
        result = result.trim();


        System.out.println(result);
    }
}
