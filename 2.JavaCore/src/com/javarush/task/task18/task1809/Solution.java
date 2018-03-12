package com.javarush.task.task18.task1809;

/* 
Реверс файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = brdr.readLine();
        String fileName2 = brdr.readLine();
        brdr.close();
        FileInputStream fis = new FileInputStream(fileName1);
        FileOutputStream fos = new FileOutputStream(fileName2);
        byte[] fileBuff = new byte[fis.available()];
        for(int i = fileBuff.length-1; i>=0; i--){
            fileBuff[i] = (byte)fis.read();
        }
        fos.write(fileBuff);
        fis.close();
        fos.close();
    }
}
