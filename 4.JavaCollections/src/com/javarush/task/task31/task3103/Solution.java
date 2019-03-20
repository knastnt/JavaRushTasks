package com.javarush.task.task31.task3103;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/* 
Своя реализация
*/
public class Solution {
    public static byte[] readBytes(String fileName) throws IOException {
        /*FileInputStream fis = new FileInputStream(fileName);
        byte[] result = new byte[fis.available()];
        fis.read(result);
        fis.close();*/
        return Files.readAllBytes(new File(fileName).toPath());
    }

    public static List<String> readLines(String fileName) throws IOException {
        return Files.readAllLines(new File(fileName).toPath());
    }

    public static void writeBytes(String fileName, byte[] bytes) throws IOException {
        Files.write(new File(fileName).toPath(), bytes);
    }

    public static void copy(String resourceFileName, String destinationFileName) throws IOException {
        Files.copy(new File(resourceFileName).toPath(), new File(destinationFileName).toPath());
    }
}
