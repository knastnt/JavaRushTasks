package com.javarush.task.task19.task1915;

/* 
Дублируем текст
*/

import java.io.*;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException {
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        String fileName = brdr.readLine();
        brdr.close();

        PrintStream printStream = System.out;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream newPrintStream = new PrintStream(byteArrayOutputStream);

        System.setOut(newPrintStream);

        testString.printSomething();

        System.setOut(printStream);

        String result = byteArrayOutputStream.toString();

        System.out.println(result);

        FileOutputStream fos = new FileOutputStream(fileName);
        fos.write(result.getBytes());
        fos.close();
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}

