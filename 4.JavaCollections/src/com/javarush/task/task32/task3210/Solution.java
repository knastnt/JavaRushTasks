package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws IOException {
        String fileName = args[0];
        int number = Integer.valueOf(args[1]);
        String text = args[2];
        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        raf.seek(number);

        byte readBuffer[] = new byte[text.length()];
        raf.read(readBuffer, 0, text.length());


        raf.seek(raf.length());
        if (Arrays.equals(text.getBytes(), readBuffer)) {
            raf.write("true".getBytes());
        }else{
            raf.write("false".getBytes());
        }

        raf.close();
    }
}
