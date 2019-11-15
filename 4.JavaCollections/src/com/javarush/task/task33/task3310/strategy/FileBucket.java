package com.javarush.task.task33.task3310.strategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile(null,null);

            Files.deleteIfExists(path);
            Files.createFile(path);

        } catch (IOException e) {
            e.printStackTrace();
        }
        path.toFile().deleteOnExit();
    }

    public long getFileSize() { //, он должен возвращать размер файла на который указывает path.
        try {
            return Files.size(path);
        } catch (IOException e) {
        }
        return -1;
    }
    public void putEntry(Entry entry) { //- должен сериализовывать переданный entry в файл. Учти, каждый entry может содержать еще один entry.
        try {
            ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path));
            oos.writeObject(entry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Entry getEntry() { //- должен забирать entry из файла. Если файл имеет нулевой размер, вернуть null.
        if (this.getFileSize() <= 0) return null;
        try {
            ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path));
            return (Entry) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void remove() { //- удалять файл на который указывает path.
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
