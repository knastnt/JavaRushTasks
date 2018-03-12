package com.javarush.task.task18.task1810;

/* 
DownloadException
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws DownloadException, IOException {
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fis;

            String fileName;
            while(true){
                fileName = brdr.readLine();
                fis = new FileInputStream(fileName);
                if(fis.available()<1000){
                    brdr.close();
                    fis.close();
                    throw new DownloadException();
                }
            }
        
    }

    public static class DownloadException extends Exception {

    }
}
