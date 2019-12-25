package com.javarush.task.task39.task3908;

import java.util.*;

/*
Возможен ли палиндром?
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("w5cttw5wc"));
        System.out.println(isPalindromePermutation("55cwwc"));
    }

    public static boolean isPalindromePermutation(String s) {
        Map<Character, Integer> chars = new HashMap<>();
        for (char c : s.toLowerCase().toCharArray()) {
            if (chars.containsKey(c)){
                chars.put(c, chars.get(c).intValue()+1);
            }else{
                chars.put(c, 1);
            }
        }
        int even = 0;
        int odd = 0;
        for (Map.Entry<Character, Integer> e : chars.entrySet()) {
            if (e.getValue()%2==0){
                even++;
            }else{
                odd++;
            }
        }
        if (s.length()%2==0){
            return odd==0;
        }else{
            return odd==1;
        }
    }
}
