package com.javarush.task.task18.task1820;

/* 
Округление чисел
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        String f1 = brdr.readLine();
        String f2 = brdr.readLine();
        brdr.close();

        BufferedReader bfr = new BufferedReader(new FileReader(f1));
        String[] f1ContentArr = bfr.readLine().split(" ");
        bfr.close();

        FileWriter fw = new FileWriter(f2);
        for(String i : f1ContentArr){
            fw.write(String.valueOf(Math.round(Double.parseDouble(i))) + " ");
        }
        fw.close();
    }
}
