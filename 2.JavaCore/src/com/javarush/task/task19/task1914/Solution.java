package com.javarush.task.task19.task1914;

/* 
Решаем пример
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream printStream = System.out;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream newPrintStream = new PrintStream(byteArrayOutputStream);

        System.setOut(newPrintStream);

        testString.printSomething();

        System.setOut(printStream);

        String resultString = byteArrayOutputStream.toString();

        //Pattern pattern = Pattern.compile("(\\d+)\\s([+,-,*])\\s(\\d+)\\s=");
        Pattern pattern = Pattern.compile("(\\d+)\\s([+,\\-,*])\\s(\\d+)\\s=");
        Matcher matcher = pattern.matcher(resultString);
        matcher.find();
        int x1 = Integer.parseInt(matcher.group(1));
        int x2 = Integer.parseInt(matcher.group(3));
        //System.out.println(matcher.group());
        int result = 0;
        switch (matcher.group(2)){
            case "+":
                result = x1 + x2;
                break;
            case "-":
                result = x1 - x2;
                break;
            case "*":
                result = x1 * x2;
                break;
        }

        System.out.println(matcher.group(0) + " " + result);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

