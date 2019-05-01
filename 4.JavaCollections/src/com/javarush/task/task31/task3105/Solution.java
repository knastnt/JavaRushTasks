package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args.length > 0 ? args[0] : "C:/result.mp3";
        String zipFileName = args.length > 1 ? args[1] : "C:/pathToTest/test.zip";


        Map<String, byte[]> map = new HashMap<>();
        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFileName));
        //Копируем содержимое
        while (true) {
            ZipEntry e = zis.getNextEntry();

            if (e == null) { break; }

            ByteArrayOutputStream keyStream = new ByteArrayOutputStream();
            while (zis.available() > 0){
                int readed = zis.read();
                if (readed == -1) break;
                keyStream.write(readed);
            }
            keyStream.close();

            if (!("new/" + Paths.get(fileName).getFileName().toString().toLowerCase()).equals(e.getName().toLowerCase())) {
                map.put(e.toString().toLowerCase(), keyStream.toByteArray());
            }
        }
        zis.close();

        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFileName));
        ZipEntry ze = new ZipEntry("new/" + Paths.get(fileName).getFileName());
        zos.putNextEntry(ze);
        Files.copy(Paths.get(fileName), zos);
        zos.closeEntry();

        for (Map.Entry<String, byte[]> pair : map.entrySet()) {
            zos.putNextEntry(new ZipEntry(pair.getKey()));
            zos.write(pair.getValue());
            zos.closeEntry();
        }
        zos.close();


    }
}
