package com.javarush.task.task20.task2006;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Как сериализовать?
*/
public class Solution {
    public static class Human implements Serializable{
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream("D:/1.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        Human human = new Human("vanya", new Asset("car"), new Asset("home"));
        objectOutputStream.writeObject(human);
        objectOutputStream.close();
        fileOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream("D:/1.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Human newMan = (Human) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
    }
}
