package com.javarush.task.task18.task1822;

/* 
Поиск данных внутри файла
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        String file = brdr.readLine();
        brdr.close();

        brdr = new BufferedReader(new FileReader(file));
        while(brdr.ready()){
            String currentLine = brdr.readLine();
            if(currentLine.indexOf(args[0] + " ") == 0){
                System.out.println(currentLine);
                break;
            }
        }
        brdr.close();
    }
}
