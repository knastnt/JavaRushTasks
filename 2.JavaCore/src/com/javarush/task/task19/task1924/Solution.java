package com.javarush.task.task19.task1924;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Замена чисел
*/

public class Solution {
    public static Map<Integer, String> map = new HashMap<Integer, String>();

    static{
        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семь");
        map.put(8, "восемь");
        map.put(9, "девять");
        map.put(10, "десять");
        map.put(11, "одиннадцать");
        map.put(12, "двенадцать");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        String fileName = brdr.readLine();
        brdr.close();

        FileReader fr = new FileReader(fileName);
        StringBuffer fileContent = new StringBuffer();

        while(fr.ready()){
            char[] buffer = new char[10];
            int count = fr.read(buffer);
            fileContent.append(buffer, 0,count);
        }

        fr.close();

        Pattern pattern = Pattern.compile("\\b(\\d+)\\b"); //отдельно стоящая цифра
        Matcher matcher = pattern.matcher(fileContent.toString());

        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            Integer i = Integer.parseInt(matcher.group(1));
            if(map.containsKey(i)) {
                matcher.appendReplacement(result, map.get(i));    // Подставляем значение из HashMap
            }else{
                matcher.appendReplacement(result, matcher.group(0));    // Или найденное совпадение, если ключ не найден
            }
        }
        matcher.appendTail(result); // Добавить остаток строки

        System.out.println(result.toString());
    }
}
