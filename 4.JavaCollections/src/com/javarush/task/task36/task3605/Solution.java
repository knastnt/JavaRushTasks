package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        TreeSet<Character> treeSet = new TreeSet<>();
        FileInputStream fis = new FileInputStream(fileName);
        while(fis.available() > 0){
            treeSet.add(Character.toLowerCase((char)fis.read()));
        }
        fis.close();
        int i = 0;
        for (Character character : treeSet) {
            if(Character.isLetter(character)) {
                if (i > 4) break;
                System.out.print(character);
                i++;
            }
        }
    }
}
