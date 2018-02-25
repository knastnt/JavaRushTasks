package com.javarush.task.task18.task1807;

/* 
Подсчет запятых
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        String fileName = brdr.readLine();
        brdr.close();
        int count = 0;
        FileInputStream fis = new FileInputStream(fileName);
        while(fis.available()>0){
            //System.out.println(fis.read());
            if(fis.read() == 44){
                count++;
            }
        }
        fis.close();
        System.out.println(count);
    }
}
