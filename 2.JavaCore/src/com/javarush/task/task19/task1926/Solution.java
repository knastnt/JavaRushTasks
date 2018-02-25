package com.javarush.task.task19.task1926;

/* 
Перевертыши
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        String fileName = brdr.readLine();
        brdr.close();

        brdr = new BufferedReader(new FileReader(fileName));



        while(brdr.ready()){
            StringBuffer sb = new StringBuffer();
            sb.append(brdr.readLine());
            sb.reverse();
            System.out.println(sb.toString());
        }

        brdr.close();


    }
}
