package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.*;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) {
        try {
            String resultFileName = args[0];
            Set<String> setOfFiles = new TreeSet<>();
            for (int i = 1; i < args.length; i++) {
                setOfFiles.add(args[i]);
            }
            try (ByteArrayOutputStream tempZip = new ByteArrayOutputStream()) {
                for (String singleFile : setOfFiles) {
                    try (FileInputStream fis = new FileInputStream(singleFile)) {
                        byte buffer[] = new byte[fis.available()];
                        fis.read(buffer);
                        tempZip.write(buffer);
                    }
                }

                try(ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(tempZip.toByteArray()))) {
                    try(FileOutputStream fos = new FileOutputStream(new File(resultFileName))) {
                        zis.getNextEntry();
                        byte buffer[] = new byte[100];
                        while (true) {
                            int size = zis.read(buffer);
                            if (size== -1) break;
                            /* Косячим файл :) *
                            if (size == 100) {
                                buffer[50] = (byte)(buffer[50] - 128);
                            }*/
                            fos.write(buffer, 0, size);
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка ввода-вывода");
        }
    }
}
