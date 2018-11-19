package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        String fileName = brdr.readLine();
        brdr.close();

        brdr = new BufferedReader(new FileReader(fileName));

        StringBuilder sb = new StringBuilder();
        while (brdr.ready()){
            sb.append(brdr.readLine());
            sb.append(" ");
        }

        //System.out.println(sb.toString().trim());


        String[] arr = sb.toString().trim().split(" ");
        for (int i = 0; i < arr.length; i++) {
            String currentWord = arr[i];
            //System.out.println("currentWord = " + currentWord);
            for (int j = i+1; j < arr.length; j++) {
                //System.out.println(arr[j]);
                if(arr[i].equals(reverse(arr[j]))){
                    Pair newPair = new Pair(arr[i], arr[j]);
                    if(!result.contains(newPair)) result.add(newPair);
                }
            }
        }
        //System.out.println("");
    }

    public static String reverse(String start){
        StringBuilder sb = new StringBuilder(start);
        return sb.reverse().toString();
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }

        public Pair() {
        }

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }
    }

}
