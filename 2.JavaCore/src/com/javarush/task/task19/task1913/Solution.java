package com.javarush.task.task19.task1913;

/* 
Выводим только цифры
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

        String result = byteArrayOutputStream.toString();

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(result);

        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()){
            //System.out.println(matcher.group());
            stringBuffer.append(matcher.group());
        }

        System.out.println(stringBuffer.toString());
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's 1 a 23 text 4 f5-6or7 tes8ting");
        }
    }
}
