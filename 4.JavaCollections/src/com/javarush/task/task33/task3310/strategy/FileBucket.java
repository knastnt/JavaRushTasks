package com.javarush.task.task33.task3310.strategy;

import javax.tools.FileObject;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;

public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile("",null);
            File file = path.toFile();
            file.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long getFileSize() { //, он должен возвращать размер файла на который указывает path.
        return path.toFile().length();
    }
    public void putEntry(Entry entry) { //- должен сериализовывать переданный entry в файл. Учти, каждый entry может содержать еще один entry.
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(entry);
            oos.close();

            baos.flush();

            FileOutputStream fos = new FileOutputStream(path.toFile());
            fos.write(baos.toByteArray());
            fos.close();

            baos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Entry getEntry() { //- должен забирать entry из файла. Если файл имеет нулевой размер, вернуть null.
        if (path.toFile().length() == 0) return null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path.toFile()));
            baos.write(bis.readAllBytes());
            bis.close();
            baos.flush();

            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
            Entry toReturn = (Entry) ois.readObject();
            ois.close();
            baos.close();

            return toReturn;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void remove() { //- удалять файл на который указывает path.
        path.toFile().delete();
    }
}
