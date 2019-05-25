package com.javarush.task.task32.task3213;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
        System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0
    }

    public static String decode(StringReader reader, int key) {
        StringWriter sw = new StringWriter();
        int readed;
        try {
            while ((readed = reader.read()) > 0) {
                sw.append((char) (readed + key));
            }
        } catch (NullPointerException | IOException e) {
            return "";
        }
        return sw.toString();
    }
}
