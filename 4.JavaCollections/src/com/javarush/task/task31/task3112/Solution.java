package com.javarush.task.task31.task3112;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("D:/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        // implement this method
        URL url = new URL(urlString);

        Path file = Files.createTempFile("temp-", ".tmp");

        Files.copy(url.openStream(), file, StandardCopyOption.REPLACE_EXISTING);

        Path p = downloadDirectory.resolve(Paths.get(url.getFile()).getFileName());

        if (!p.getParent().toFile().exists()){
            Files.createDirectories(p.getParent());
        }

        Files.move(file, p, StandardCopyOption.REPLACE_EXISTING);

        return p;
    }
}
