package com.javarush.task.task18.task1813;

import java.io.*;
import java.nio.channels.FileChannel;

/* 
AmigoOutputStream
*/

public class AmigoOutputStream extends FileOutputStream {
    public static String fileName = "C:/tmp/result.txt";
    private FileOutputStream fileOutputStream;

    public AmigoOutputStream(FileOutputStream fileOutputStream) throws FileNotFoundException {
        super(fileName);
        this.fileOutputStream = fileOutputStream;
    }

    public static void main(String[] args) throws FileNotFoundException {
        new AmigoOutputStream(new FileOutputStream(fileName));
    }

    /*public AmigoOutputStream(String name) throws FileNotFoundException {
        super(name);
    }

    public AmigoOutputStream(String name, boolean append) throws FileNotFoundException {
        super(name, append);
    }

    public AmigoOutputStream(File file) throws FileNotFoundException {
        super(file);
    }

    public AmigoOutputStream(File file, boolean append) throws FileNotFoundException {
        super(file, append);
    }

    public AmigoOutputStream(FileDescriptor fdObj) {
        super(fdObj);
    }*/

    @Override
    public void write(int b) throws IOException {
        fileOutputStream.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        fileOutputStream.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        fileOutputStream.write(b, off, len);
    }

    @Override
    public void close() throws IOException {
        fileOutputStream.flush();
        fileOutputStream.write("JavaRush © All rights reserved.".getBytes());
        fileOutputStream.close();
    }

    /*@Override
    public FileChannel getChannel() {
        return super.getChannel();
    }

    @Override
    protected void finalize() throws IOException {
        super.finalize();
    }*/

    @Override
    public void flush() throws IOException {
        fileOutputStream.flush();
    }

    /*@Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }*/
}
