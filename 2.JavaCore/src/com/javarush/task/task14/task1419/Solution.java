package com.javarush.task.task14.task1419;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/* 
Нашествие исключений
*/

public class Solution {
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args) {
        initExceptions();

        for (Exception exception : exceptions) {
            System.out.println(exception);
        }
    }

    private static void initExceptions() {   //it's first exception
        try {
            float i = 1 / 0;

        } catch (Exception e) {
            exceptions.add(e);
        }
        try {
            FileInputStream f = new FileInputStream("C:/hgfdr.txt");

        } catch (Exception e) {
            exceptions.add(e);
        }
        try {
            float[] j = new float[5];
            j[9] = 6;

        } catch (Exception e) {
            exceptions.add(e);
        }
        try {
            Class c = Class.forName("hjgf");
        } catch (Exception e) {
            exceptions.add(e);
        }
        try {
            throw new IndexOutOfBoundsException();
        } catch (Exception e) {
            exceptions.add(e);
        }
        try {
            Object s = "kkk";
            int i = (Integer)s;
        } catch (Exception e) {
            exceptions.add(e);
        }
        try {
            Integer.parseInt(null);
        } catch (Exception e) {
            exceptions.add(e);
        }
        try {
            Double.parseDouble(null);
        } catch (Exception e) {
            exceptions.add(e);
        }
        try {
            throw new IllegalArgumentException();
        } catch (Exception e) {
            exceptions.add(e);
        }
        try {
            throw new NoSuchElementException();
        } catch (Exception e) {
            exceptions.add(e);
        }

        //напишите тут ваш код

    }
}
