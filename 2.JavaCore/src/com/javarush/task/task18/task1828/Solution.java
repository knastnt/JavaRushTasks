package com.javarush.task.task18.task1828;

/* 
Прайсы 2
*/

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Solution {
    public static void main(String[] args) throws IOException {
        /*args = new String[2];
        args[0] = "-d";
        args[1] = "198478";*/

        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        String fileName = brdr.readLine();
        brdr.close();

        try {
            if (args.length == 5 && "-u".equals(args[0])) {
                update(args, fileName);
            }else {
                if (args.length == 2 && "-d".equals(args[0])) {
                    delete(args, fileName);
                }else{
                    throw new Exception("Неверные параметры");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void update(String[] args, String fileName) throws IOException {
        ArrayList<String> hashList = readFile(fileName);
        String id = toFixedSizeString(args[1], 8);
        String productName = toFixedSizeString(args[2], 30);
        String price = toFixedSizeString(args[3], 8);
        String quantity = toFixedSizeString(args[4], 4);
        for(int i=0; i<hashList.size(); i++){
            if(hashList.get(i).substring(0,8).equals(id)){
                hashList.set(i, id + productName + price + quantity);
            }
        }
        writeFile(fileName, hashList);
    }

    public static void delete(String[] args, String fileName) throws IOException {
        ArrayList<String> hashList = readFile(fileName);
        String id = toFixedSizeString(args[1], 8);
        for(int i=0; i<hashList.size(); i++){
            if(hashList.get(i).substring(0,8).equals(id)){
                hashList.remove(i);
            }
        }
        writeFile(fileName, hashList);
    }

    private static ArrayList<String> readFile(String fileName) throws IOException {
        BufferedReader brdr = new BufferedReader(new FileReader(fileName));
        Object[] resultArr = brdr.lines().toArray();
        brdr.close();

        ArrayList<String> resultList = new ArrayList<String>();
        for(Object o : resultArr){
            if(o.toString() != null && o.toString() != "") {
                resultList.add(o.toString());
            }
        }

        return resultList;
    }

    private static void writeFile(String fileName, ArrayList<String> set) throws IOException {
        BufferedWriter bfwr = new BufferedWriter(new FileWriter(fileName));
        for(String s : set){
            bfwr.write(s);
            bfwr.newLine();
        }
        bfwr.close();
    }

    public static String toFixedSizeString(String string, int len){
        if(string.length() > len){
            return string.substring(0, len);
        }
        if(string.length() < len){
            StringBuilder stb = new StringBuilder();
            stb.append(string);
            for(int i = 0; i < len - string.length(); i++){
                stb.append(" ");
            }
            return stb.toString();
        }
        return string;
    }
}
