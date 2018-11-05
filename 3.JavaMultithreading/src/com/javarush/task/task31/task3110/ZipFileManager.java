package com.javarush.task.task31.task3110;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileManager {
    private Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }

    public void createZip(Path source) throws Exception{
        //try(ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile.toFile()))) {
        try(ZipOutputStream zipOutputStream = (ZipOutputStream) Files.newOutputStream(zipFile)) {
            ZipEntry zipEntry = new ZipEntry(source.getFileName().toString());
            zipOutputStream.putNextEntry(zipEntry);

            //try (InputStream inputStream = new FileInputStream(source.toFile())) {
            try (InputStream inputStream = Files.newInputStream(source)) {
                byte[] buffer = new byte[1024];
                while(inputStream.available()>0){
                    int count = inputStream.read(buffer);
                    zipOutputStream.write(buffer,0,count);
                }
            }
            zipOutputStream.closeEntry();
        }
    }
}
