package com.javarush.task.task39.task3904;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Лестница
*/
public class Solution {
    private static int n = 70;
    private static long[] results = new long[n+1];
    private static int resultsLastFilled;
    static {
        results[0] = 1;
        results[1] = 1;
        results[2] = 2;
        resultsLastFilled = 2;
    }

    public static void main(String[] args) {
        for(int i=0; i<10; i++){
            System.out.println(i +" = "+numberOfPossibleAscents(i));
        }


        System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n));
    }

    public static long numberOfPossibleAscents(int n) {
        if (n<0) return 0;
        if (results[n] == 0){
            for(int i = resultsLastFilled; i < n; i++){
                results[i+1] = results[i] + results[i-1] + results[i-2];
                resultsLastFilled = i+1;
            }
        }
        return results[n];
    }
}

