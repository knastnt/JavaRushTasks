package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        String fileName = brdr.readLine();
        brdr.close();

        StringBuilder sb = new StringBuilder();

        FileReader fr = new FileReader(fileName);
        char[] buffer = new char[5];
        while(fr.ready()){
            int count = fr.read(buffer);
            sb.append(buffer, 0, count);
        }
        fr.close();

        String[] arr = sb.toString().split(" ");

        StringBuilder result = getLine(arr);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        if(words == null) return null;

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            StringBuilder current = getLineTryIndex(words.clone(), i);
            if(result.length() < current.length()) result=current;
        }

        return result;
    }

    public static StringBuilder getLineTryIndex(String[] words, int startIndex) {
        if(words == null) return null;

        ArrayList<String> arr = new ArrayList<>();
        Arrays.sort(words);
        arr.addAll(Arrays.asList(words));

        StringBuilder sb = new StringBuilder();

        while (arr.size() != 0){
            int index = startIndex;

            if(sb.length() > 0) index = findNextWordIndex(sb, arr);

            if(index == -1) break;

            sb.append(arr.get(index) + " ");
            arr.remove(index);
        }



        return sb;
    }



    public static int findNextWordIndex(StringBuilder sb, ArrayList<String> arr){
        char lastCharacter = sb.charAt(sb.length()-2);
        String lastCharacterSt = Character.toString(lastCharacter).toUpperCase();
        for (int i = 0; i < arr.size(); i++) {
            char currentCharacter = arr.get(i).charAt(0);
            String currentCharacterSt = Character.toString(currentCharacter).toUpperCase();
            if(currentCharacterSt.equals(lastCharacterSt)) return i;

        }
        return -1;
    }
}
