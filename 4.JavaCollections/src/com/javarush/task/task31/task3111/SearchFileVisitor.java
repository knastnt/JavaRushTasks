package com.javarush.task.task31.task3111;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName, partOfContent;
    private Integer minSize, maxSize;
    private List<Path> foundFiles = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        //byte[] content = Files.readAllBytes(file); // размер файла: content.length

        boolean willAdd = true;

        if (willAdd && minSize != null && attrs.size() < minSize) { willAdd = false; }
        if (willAdd && maxSize != null && attrs.size() > maxSize) { willAdd = false; }
        if (willAdd && partOfName != null && !file.getFileName().toString().toLowerCase().contains(partOfName.toLowerCase())){ willAdd = false; }
        if (willAdd && partOfContent != null) {
            willAdd = false;
            List<String> lines = Files.readAllLines(file);
            for (String line : lines) {
                if (line.toLowerCase().contains(partOfContent.toLowerCase())){
                    willAdd = true;
                    break;
                }
            }
        }

        if (willAdd) { foundFiles.add(file); }

        return super.visitFile(file, attrs);
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }
}
