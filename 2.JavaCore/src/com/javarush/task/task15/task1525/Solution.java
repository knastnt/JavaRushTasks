package com.javarush.task.task15.task1525;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/* 
Файл в статическом блоке
*/

public class Solution {
    public static List<String> lines = new ArrayList<String>();

    static{
        /*try {
            BufferedReader brdr = new BufferedReader(new InputStreamReader(new FileInputStream(Statics.FILE_NAME)));
            String inputted;
            while (true){
                inputted = brdr.readLine();
                if (inputted == null) break;
                lines.add(inputted);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        try {
            lines = Files.readAllLines(Paths.get(Statics.FILE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(lines);
    }
}
