package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.command.ExitCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Archiver {
    public static void main(String[] args) {
        System.out.println("Введите полный путь к будущему архиву:");
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        String fileName = null;
        try {
            fileName = brdr.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ZipFileManager zipFileManager = new ZipFileManager(Paths.get(fileName));

        System.out.println("Введите полный путь к файлу:");
        try {
            fileName = brdr.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            zipFileManager.createZip(Paths.get(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }

        new ExitCommand().execute();
    }
}
