package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(args[0]);
        /*char i = 'Z'; //97 - 122 //65 - 90
        System.out.println((byte)i);*/
        int count = 0;
        while (fis.available()>0){
            int rd = fis.read();
            if((rd >= 65 && rd <= 90) || (rd >= 97 && rd <= 122)){
                count++;
            }
        }
        fis.close();
        System.out.println(count);
    }
}
