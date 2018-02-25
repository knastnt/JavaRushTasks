package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = brdr.readLine();
        String fileName2 = brdr.readLine();
        brdr.close();
        //fileName1 = "D:/1.txt";
        //fileName2 = "D:/2.txt";
        brdr = new BufferedReader(new FileReader(fileName1));
        BufferedWriter bwtr = new BufferedWriter(new FileWriter(fileName2));
        while (brdr.ready()) {
            String line = brdr.readLine();


            String string = "\\b\\d+\\b";
            Pattern pattern = Pattern.compile(string);
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                    /*System.out.println("Found at: "+ matcher.start()
                            +
                            " - " + matcher.end());*/
                //System.out.println(matcher.group());

                bwtr.write(matcher.group() + " ");
            }


        }
        brdr.close();
        bwtr.close();
    }
}
