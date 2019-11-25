package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/* 
Древний Рим
*/
public class Solution {
    private static Map<Character, Integer> convert = new TreeMap<>();
    static {
        convert.put('I', 1);
        convert.put('V', 5);
        convert.put('X', 10);
        convert.put('L', 50);
        convert.put('C', 100);
        convert.put('D', 500);
        convert.put('M', 1000);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        Queue<Integer> queue = new LinkedList<>();
        for (char character : s.toUpperCase().toCharArray()) {
            if (!convert.containsKey(character))
                continue;
            queue.add(convert.get(character));
        }
        int result = 0;
        while (!queue.isEmpty()){
            int x = queue.poll();
            if (!queue.isEmpty() && queue.peek()>x){
                x = queue.poll() - x;
            }
            result += x;
        }
        return result;
    }
}
