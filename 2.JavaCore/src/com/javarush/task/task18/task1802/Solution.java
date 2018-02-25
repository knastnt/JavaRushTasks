package com.javarush.task.task18.task1802;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Минимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        String fileName = brdr.readLine();
        brdr.close();
        int min = -1;
        FileInputStream ins = new FileInputStream(fileName);
        while (ins.available()>0){
            int newi = ins.read();
            if (min > newi || min == -1){
                min = newi;
            }
        }
        ins.close();
        System.out.println(min);
    }
}
