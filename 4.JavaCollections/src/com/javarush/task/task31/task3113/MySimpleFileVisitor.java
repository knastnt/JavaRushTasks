package com.javarush.task.task31.task3113;

import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;

public abstract class MySimpleFileVisitor extends SimpleFileVisitor<Path> {
    abstract int getDirCount();
    abstract int getFileCount();
    abstract int getTotalBytes();
}
