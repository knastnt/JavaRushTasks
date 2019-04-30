package com.javarush.task.task31.task3105;

import java.io.*;
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

        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));

        FileInputStream fis = new FileInputStream(file);

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ZipOutputStream zipBuffer = new ZipOutputStream(buffer);

        //Копируем содержимое
        while (true) {
            ZipEntry e = zis.getNextEntry();

            if (e == null) { break; }

            zipBuffer.putNextEntry(e);

            while (zis.available() > 0){
                int readed = zis.read();
                if (readed == -1) break;

                zipBuffer.write(readed);
            }
        }

        //Вставляем mp3
        ZipEntry e = new ZipEntry(file.getName());
        zipBuffer.putNextEntry(e);
        while (fis.available() > 0) {
            zipBuffer.write(fis.read());
        }



        fis.close();
        zipBuffer.close();

        FileOutputStream zipFileOut = new FileOutputStream(zipFile);

        zipFileOut.write(buffer.toByteArray());
        zipFileOut.close();

        System.out.println("");
    }
}
