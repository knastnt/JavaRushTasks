package com.javarush.task.task19.task1910;

/* 
Пунктуация
*/

import java.io.*;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        String file1 = brdr.readLine();
        String file2 = brdr.readLine();
        brdr.close();

        //String file1 = "D:/1.txt";
        //String file2 = "D:/2.txt";

        brdr = new BufferedReader(new FileReader(file1));
        BufferedWriter bfwr = new BufferedWriter(new FileWriter(file2));
        while (brdr.ready()){
            String readed = brdr.readLine();

            Pattern p = Pattern.compile("[^\\s\\w]");
            System.out.println(p.matcher(readed).replaceAll(""));
            bfwr.write(p.matcher(readed).replaceAll(""));
        }
        brdr.close();
        bfwr.close();
    }
}
