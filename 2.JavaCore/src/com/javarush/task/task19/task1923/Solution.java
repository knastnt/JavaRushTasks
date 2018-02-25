package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName1 = args[0];
        String fileName2 = args[1];

        FileReader fr = new FileReader(fileName1);

        StringBuffer sb = new StringBuffer();
        char[] buffer = new char[10];
        while (fr.ready()){
            int count = fr.read(buffer);
            sb.append(buffer,0,count);
        }
        fr.close();
        String[] wordsArray = sb.toString().split("\\s|\\n");

        StringBuffer result = new StringBuffer();

        for (String s : wordsArray) {
            Pattern pattern = Pattern.compile(".*\\d.*");
            Matcher matcher = pattern.matcher(s);

            if (matcher.find()) {
                result.append(s);
                result.append(" ");
            }
        }
        result.deleteCharAt(result.length()-1);

        FileWriter fw = new FileWriter(fileName2);
        fw.write(result.toString());
        fw.close();
    }
}
