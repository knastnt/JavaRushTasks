package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        String inFile = args[1];
        String outFile = args[2];
        FileInputStream fis = new FileInputStream(inFile);
        FileOutputStream fos = new FileOutputStream(outFile);
        int salt = 0;
        if("-e".equals(args[0])){
            salt = 1;
        }
        if("-d".equals(args[0])){
            salt = -1;
        }
        while (fis.available() > 0){
            fos.write(fis.read() + salt);
        }
        fos.close();
        fis.close();

        /*String fn = "D:/111.txt";
        FileOutputStream fos = new FileOutputStream(fn);
        fos.write(-1);
        fos.close();
        FileInputStream fis = new FileInputStream(fn);

        System.out.println(fis.read());
        fis.close();*/
    }

}
