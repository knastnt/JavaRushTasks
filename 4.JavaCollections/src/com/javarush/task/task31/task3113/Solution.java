package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        File dir = new File(brdr.readLine());
        brdr.close();



        if (dir.isDirectory()){
            /*String list[] = dir3.list();
            for (String s : list) {
                System.out.println(s);
            }*/
            MySimpleFileVisitor spv = new MySimpleFileVisitor(){
                private int dirCount = -1;
                private int fileCount = 0;
                private int totalBytes = 0;

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    fileCount++;
                    totalBytes += file.toFile().length();
                    return super.visitFile(file, attrs);
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    dirCount++;
                    return super.preVisitDirectory(dir, attrs);
                }

                public int getDirCount() {
                    return dirCount;
                }

                public int getFileCount() {
                    return fileCount;
                }

                public int getTotalBytes() {
                    return totalBytes;
                }
            };



            Files.walkFileTree(Paths.get(dir.getAbsolutePath()), spv);


            System.out.format("Всего папок - %d\n", spv.getDirCount());
            System.out.format("Всего файлов - %d\n", spv.getFileCount());
            System.out.format("Общий размер - %d", spv.getTotalBytes());
        }else{
            System.out.format("%s - не папка", dir);
        }
    }
}
