package com.javarush.task.task39.task3904;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Лестница
*/
public class Solution {
    private static int n = 70;
    public static void main(String[] args) {
        for(int i=0; i<10; i++){
            System.out.println(i +" = "+numberOfPossibleAscents(i));
        }


        System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n));
    }

    public static long numberOfPossibleAscents(int n) {
        if (n<0) return 0;
        int[] num = {1,1,2};
        if (n<3) return num[n];

        for(int i = 2; i < n; i++){
            int sum = num[0] + num[1] + num[2];
            num[0] = num[1];
            num[1] = num[2];
            num[2] = sum;
        }
        return num[2];
    }

//    public static long numberOfPossibleAscents(int n) {
//        if (n<0) return 0;
//        List<Integer> results = new ArrayList<>(Arrays.asList(1,1,2));
//        if (n > results.size()-1){
//            for(int i = results.size()-1; i < n; i++){
//                results.add(results.get(results.size()-1) + results.get(results.size()-2) + results.get(results.size()-3));
//            }
//        }
//        return results.get(n);
//    }

//    public static long numberOfPossibleAscents(int n) {
//        if (n<0) return 0;
//        if (n==0) return 1;
//        return numberOfPossibleAscents(n-3) + numberOfPossibleAscents(n-2) + numberOfPossibleAscents(n-1);
//    }
}

