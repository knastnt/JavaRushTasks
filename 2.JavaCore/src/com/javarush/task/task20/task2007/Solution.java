package com.javarush.task.task20.task2007;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Как сериализовать JavaRush?
*/
public class Solution {
    public static class JavaRush implements Serializable {
        public List<User> users = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        String fileName = "D:/1.txt";
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        JavaRush javaRush = new JavaRush();
        User user = new User();
        user.setBirthDate(new Date());
        user.setCountry(User.Country.RUSSIA);
        user.setFirstName("igor");
        user.setLastName("fandi");
        user.setMale(true);
        javaRush.users.add(user);
        User user2 = new User();
        user2.setBirthDate(new Date());
        user2.setCountry(User.Country.OTHER);
        user2.setFirstName("kate");
        user2.setLastName("magnes");
        user2.setMale(false);
        javaRush.users.add(user2);
        objectOutputStream.writeObject(javaRush);
        objectOutputStream.close();
        fileOutputStream.close();
    }
}
