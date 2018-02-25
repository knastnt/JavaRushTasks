package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        String f1 = brdr.readLine();
        String f2 = brdr.readLine();
        brdr.close();

        FileInputStream fis = new FileInputStream(f1);
        byte[] f1Content = new byte[fis.available()];
        fis.read(f1Content);
        fis.close();
        //System.out.println(f1Content.toString());
        FileOutputStream fos = new FileOutputStream(f1);
        fis = new FileInputStream(f2);
        while(fis.available()>0){
            fos.write(fis.read());
        }
        fos.write(f1Content);
        fis.close();
        fos.close();
    }
}
