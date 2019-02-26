package com.javarush.task.task30.task3009;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }

    private static Set<Integer> getRadix(String digit){
        HashSet<Integer> toReturn = new HashSet<Integer>();
        try {
            BigInteger bigInteger = new BigInteger(String.valueOf(Integer.parseInt(digit)));
            for (int i = 2; i <= 36; i++) {
                //System.out.println(bigInteger.toString(i));
                if (isPolyndrome(bigInteger.toString(i))) {
                    toReturn.add(i);
                }
            }
        }catch (NumberFormatException e){}
        return toReturn;
    }

    private static boolean isPolyndrome(String digit){
        return new StringBuffer(digit).reverse().toString().toLowerCase().equals(digit.toLowerCase());
    }
}