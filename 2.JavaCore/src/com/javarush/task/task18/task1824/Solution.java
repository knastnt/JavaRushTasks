package com.javarush.task.task18.task1824;

/* 
Файлы и исключения
*/

import java.io.*;

public class Solution {
    private static boolean isStoped = false;

    public static void main(String[] args) {
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        String fname = "";
        try {
            while (true) {
                fname = brdr.readLine();
                FileInputStream fis = new FileInputStream(fname);
                fis.close();
            }
        }
        catch (FileNotFoundException fnf){
            System.out.println(fname);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
