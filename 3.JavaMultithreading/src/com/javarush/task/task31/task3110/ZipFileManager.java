package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.exception.PathIsNotFoundException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileManager {
    private Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }

    public void createZip(Path source) throws Exception{
        if(!Files.exists(zipFile.getParent())){
            Files.createDirectories(zipFile.getParent());
        }
        try(ZipOutputStream zipOutputStream =new ZipOutputStream(Files.newOutputStream(zipFile))) {

            if(Files.isRegularFile(source)){
                addNewZipEntry(zipOutputStream,source.getParent(),source.getFileName());
            }else if(Files.isDirectory(source)){
                List<Path> fileNames = new FileManager(source).getFileList();
                for (Path fileName : fileNames) {
                    addNewZipEntry(zipOutputStream, source, fileName);
                }
            }else{
                throw new PathIsNotFoundException();
            }
        }
    }

    private void addNewZipEntry(ZipOutputStream zipOutputStream, Path filePath, Path fileName) throws Exception{
        try (InputStream inputStream = Files.newInputStream(filePath.resolve(fileName))) {
        //try (InputStream inputStream = Files.newInputStream(filePath)) {
            //ZipEntry zipEntry = new ZipEntry(fileName.getFileName().toString());
            ZipEntry zipEntry = new ZipEntry(fileName.toString());
            zipOutputStream.putNextEntry(zipEntry);

            copyData(inputStream,zipOutputStream);

            zipOutputStream.closeEntry();
        }
    }

    private void copyData(InputStream in, OutputStream out) throws Exception{
        byte[] buffer = new byte[1024];
        while(in.available()>0){
            int count = in.read(buffer);
            out.write(buffer,0,count);
        }
    }
}
