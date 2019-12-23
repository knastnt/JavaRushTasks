package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/* 
Уникальные подстроки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        int result = 0;
        if (s != null && !s.equals("")) {
            Set<Character> resultArray = new HashSet<>();
            for (char c : s.toCharArray()) {
                if (resultArray.contains(c)) {
                    if (result < resultArray.size()) result = resultArray.size();
                    resultArray.clear();
                }
                resultArray.add(c);
            }
            if (result < resultArray.size()) result = resultArray.size();
        }
        return result;
    }
}
