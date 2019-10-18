package com.javarush.task.task37.task3707;

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        String zero = "ggggg";
        AmigoSet<String> one = new AmigoSet<>();
        one.add("hello1");
        one.add("hello2");
        one.add("hello3");
        AmigoSet<String> two = one.clone();
        two.add("amigo");

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(baos);
            os.writeObject(one);
            os.close();

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream is = new ObjectInputStream(bais);
            Object three = is.readObject();
            is.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("");

    }
}
