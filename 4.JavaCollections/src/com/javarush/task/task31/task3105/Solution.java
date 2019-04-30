package com.javarush.task.task31.task3105;

import java.io.*;
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

        File file = new File(fileName);
        File zipFile = new File(zipFileName);

        if (!file.exists() || !zipFile.exists()) { return; }

        HashMap<String, ByteArrayOutputStream> map = new HashMap<>();

        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));

        //Копируем содержимое
        while (true) {
            ZipEntry e = zis.getNextEntry();

            if (e == null) { break; }

            ByteArrayOutputStream key = new ByteArrayOutputStream();
            while (zis.available() > 0){
                int readed = zis.read();
                if (readed == -1) break;
                key.write(readed);
            }
            key.close();

            map.put(e.toString().toLowerCase(), key);
        }
        zis.close();

        //Вставляем mp3
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream key = new ByteArrayOutputStream();
        while (fis.available() > 0){
            int readed = fis.read();
            key.write(readed);
        }
        key.close();
        fis.close();
        map.put("new/" + file.getName().toLowerCase(), key);



        ZipOutputStream zipFileOut = new ZipOutputStream(new FileOutputStream(zipFile));

        for (Map.Entry<String, ByteArrayOutputStream> pair : map.entrySet()) {
            zipFileOut.putNextEntry(new ZipEntry(pair.getKey()));
            zipFileOut.write(pair.getValue().toByteArray());
        }

        zipFileOut.close();
    }
}
