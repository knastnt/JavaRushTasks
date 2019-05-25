package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.util.Random;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        boolean numbers;
        boolean uppers;
        boolean lowers;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Random r = new Random();

        while (true) {
            numbers = false;
            uppers = false;
            lowers = false;
            baos.reset();

            while (baos.size() < 8) {
                char maybeThis = (char) (48 + r.nextInt(75));
                if (Character.isDigit(maybeThis)) {
                    numbers = true;
                    baos.write(maybeThis);
                }
                if (Character.isLetter(maybeThis)) {
                    if (Character.isUpperCase(maybeThis)) {
                        uppers = true;
                    } else {
                        lowers = true;
                    }
                    baos.write(maybeThis);
                }
            }

            if (numbers && uppers && lowers) {
                break;
            }
        }

        return baos;
    }
}