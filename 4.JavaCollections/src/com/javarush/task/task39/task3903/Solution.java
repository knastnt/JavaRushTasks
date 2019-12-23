package com.javarush.task.task39.task3903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Неравноценный обмен
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Please enter a number: ");

        long number = Long.parseLong(reader.readLine());
        System.out.println("Please enter the first index: ");
        int i = Integer.parseInt(reader.readLine());
        System.out.println("Please enter the second index: ");
        int j = Integer.parseInt(reader.readLine());

        System.out.println("The result of swapping bits is " + swapBits(number, i, j));
    }

    public static long swapBits(long number, int i, int j) {
        String zeros = "";
        for (int k = 0; k < Math.max(i,j); k++) {
            zeros = zeros + "0";
        }
        char[] longChars = (zeros + Long.toString(number, 2)).toCharArray();

        char one = longChars[longChars.length-i-1];
        char two = longChars[longChars.length-j-1];

        longChars[longChars.length-i-1] = two;
        longChars[longChars.length-j-1] = one;

        return Long.parseLong(String.valueOf(longChars), 2);
    }
}
