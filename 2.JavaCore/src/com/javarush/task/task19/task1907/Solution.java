package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        String fileName = brdr.readLine();
        brdr.close();
        //fileName = "D:/1.txt";
        brdr = new BufferedReader(new FileReader(fileName));
        int count = 0;
        while (brdr.ready()) {
            String line = brdr.readLine();


            String string = "\\bworld\\b";
            Pattern pattern = Pattern.compile(string);
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                    /*System.out.println("Found at: "+ matcher.start()
                            +
                            " - " + matcher.end());*/
                count++;
            }


        }
        brdr.close();
        System.out.println(count);
    }
}
