package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName1 = args[0];
        String fileName2 = args[1];

        FileReader fr = new FileReader(fileName1);
        StringBuffer sb = new StringBuffer();

        while(fr.ready()){
            char[] buffer = new char[10];
            int count = fr.read(buffer);
            sb.append(buffer, 0, count);
        }

        fr.close();

        String[] words = sb.toString().split("\\s|\\n");

        StringBuffer resultString = new StringBuffer();

        for (String word : words) {
            if(word.length()>6){
                resultString.append(word);
                resultString.append(",");
            }
        }

        if(resultString.length()>0){
            resultString.deleteCharAt(resultString.length()-1);
        }

        FileWriter fileWriter = new FileWriter(fileName2);
        fileWriter.write(resultString.toString());
        fileWriter.close();
    }
}
