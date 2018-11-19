package com.javarush.task.task20.task2009;

import java.io.*;

/*
Как сериализовать static?
*/
public class Solution {
    public static class ClassWithStatic implements Serializable {
        public static String staticString = "it's test static string";
        public int i;
        public int j;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ClassWithStatic classWithStatic = new ClassWithStatic();
        classWithStatic.i = 7;
        classWithStatic.j = 89;

        String fileName = "D:/1.txt";

        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(classWithStatic);
        oos.close();
        fos.close();
        System.out.println(ClassWithStatic.staticString);

        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ClassWithStatic classWithStatic2 = (ClassWithStatic) ois.readObject();
        ois.close();
        fis.close();
        System.out.println(ClassWithStatic.staticString);
    }
}
