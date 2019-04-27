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

            SimpleFileVisitor spv = new SimpleFileVisitor<Path>(){
                int dirCount = -1;
                int fileCount = 0;
                int totalBytes = 0;

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    this.fileCount++;
                    totalBytes += file.toFile().length();
                    return super.visitFile(file, attrs);
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    dirCount++;
                    return super.preVisitDirectory(dir, attrs);
                }

                @Override
                public String toString() {
                    return String.format("Всего папок - %d\nВсего файлов - %d\nОбщий размер - %d", dirCount, fileCount, totalBytes);
                }
            };



            Files.walkFileTree(Paths.get(dir.getAbsolutePath()), spv);

            System.out.println(spv);
        }else{
            System.out.format("%s - не папка", dir);
        }
    }

}
