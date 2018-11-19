package com.javarush.task.task22.task2210;

import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {
        String [] s = getTokens("level22.lesson13.task01", ".");
        System.out.println("");
    }
    public static String [] getTokens(String query, String delimiter) {
        StringTokenizer stringTokenizer = new StringTokenizer(query, delimiter);

                String[] toreturn = new String[stringTokenizer.countTokens()];

                int i=0;
                while (stringTokenizer.hasMoreTokens()){
                    toreturn[i] = stringTokenizer.nextToken();
                    i++;
                }
        return toreturn;
    }
}
