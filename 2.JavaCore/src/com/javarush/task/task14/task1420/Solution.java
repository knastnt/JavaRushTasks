package com.javarush.task.task14.task1420;

/* 
НОД
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        try{
            int one = Integer.parseInt(brdr.readLine());
            int two = Integer.parseInt(brdr.readLine());
            if (one <= 0 || two <=0) throw new Exception();
            System.out.println(getNOD(one, two));
        }
        catch (Exception e){
            //System.out.println(e);
            throw e;
        }
    }

    static int getNOD(int a, int b){
        int current = (a > b) ? b : a;
        while (true) {
            if (a%current == 0 && b%current == 0){
                return current;
            }
            current--;
        }
    }
}
