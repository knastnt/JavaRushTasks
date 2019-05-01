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


        Map<String, byte[]> map = new HashMap<>();
        ZipInputStream zis = new ZipInputStream(new FileInputStream(args[1]));
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

            if (!("new/" + Paths.get(args[0]).getFileName().toString().toLowerCase()).equals(e.getName().toLowerCase())) {
                map.put(e.toString().toLowerCase(), keyStream.toByteArray());
            }
        }
        zis.close();

        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(args[1]));
        ZipEntry ze = new ZipEntry("new/" + Paths.get(args[0]).getFileName());
        zos.putNextEntry(ze);
        Files.copy(Paths.get(args[0]), zos);
        zos.closeEntry();

        for (Map.Entry<String, byte[]> pair : map.entrySet()) {
            zos.putNextEntry(new ZipEntry(pair.getKey()));
            zos.write(pair.getValue());
            zos.closeEntry();
        }
        zos.close();


    }
}
