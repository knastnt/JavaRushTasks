package com.javarush.task.task18.task1801;

import java.io.*;

/* 
Максимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        String fileName = brdr.readLine();
        /*InputStreamReader inst = new InputStreamReader(new FileInputStream(fileName));
        int max = 0;
        while (inst.ready()){
            int newI = inst.read();
            if(max < newI){
                max = newI;
            }
        }*/
        FileInputStream inst = new FileInputStream(fileName);
        int max = 0;
        while (inst.available()>0){
            int newI = inst.read();
            if(max < newI){
                max = newI;
            }
            //System.out.println(inst.read());
        }
        System.out.println(max);
        brdr.close();
        inst.close();
    }
}
