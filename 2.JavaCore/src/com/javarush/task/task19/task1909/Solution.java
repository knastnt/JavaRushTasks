package com.javarush.task.task19.task1909;

/* 
Замена знаков
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        String file1 = brdr.readLine();
        String file2 = brdr.readLine();
        brdr.close();

        brdr = new BufferedReader(new FileReader(file1));
        BufferedWriter bwrr = new BufferedWriter(new FileWriter(file2));
        while (brdr.ready()){
            String line = brdr.readLine();
            bwrr.write(line.replace(".", "!"));
            //bwrr.newLine();
        }
        brdr.close();
        bwrr.close();
    }
}
