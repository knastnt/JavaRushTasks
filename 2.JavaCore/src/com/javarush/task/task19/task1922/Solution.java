package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        String fileName = brdr.readLine();
        brdr.close();

        brdr = new BufferedReader(new FileReader(fileName));
        Object[] fileContentArray = brdr.lines().toArray();
        brdr.close();

        for(Object o : fileContentArray){
            if(isTwoWords((String)o)){
                System.out.println((String)o);
            }
        }
    }

    private static boolean isTwoWords(String text){
        text = " " + text + " ";
        int wordsCount = 0;
        for (int i = 0; i < words.size(); i++) {
            if(text.indexOf(" " + words.get(i) + " ") != -1){
                wordsCount++;
            }
        }
        if(wordsCount == 2){
            return true;
        }else{
            return false;
        }

    }
}
