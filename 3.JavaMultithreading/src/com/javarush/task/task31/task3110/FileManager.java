package com.javarush.task.task31.task3110;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileManager {
    private Path rootPath;
    private List<Path> fileList = new ArrayList<>();

    public FileManager(Path rootPath) throws IOException {
        this.rootPath = rootPath;
        collectFileList(rootPath);
    }

    public List<Path> getFileList() {
        return fileList;
    }

    private void collectFileList(Path path) throws IOException{
        //System.out.println("collectFileList( " + path.toString() + " )");
        if(Files.isRegularFile(path)){
            Path otnosit = rootPath.relativize(path);
            fileList.add(otnosit);
            //System.out.println("Это файл, добавляем: " + otnosit);
        }else if(Files.isDirectory(path)){
            try(DirectoryStream directoryStream = Files.newDirectoryStream(path)){
                Iterator iterator = directoryStream.iterator();
                while (iterator.hasNext()){
                    Path path1 = (Path) iterator.next();
                    //System.out.println("iterator " + path1);
                    collectFileList(path1);
                }
            }
        }
    }
}
