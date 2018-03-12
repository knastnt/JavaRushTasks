package com.javarush.task.task18.task1827;

/* 
Прайсы
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //System.out.println(toFixedSizeString("123", 7));

        if (args.length != 4){
            return;
        }
        if(!"-c".equals(args[0])){
            return;
        }
        String productName = toFixedSizeString(args[1], 30);
        String price = toFixedSizeString(args[2], 8);
        String quantity = toFixedSizeString(args[3], 4);

        String fileName;
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        fileName = brdr.readLine();
        brdr.close();

        brdr = new BufferedReader(new FileReader(fileName));
        int maxId = 0;
        Object[] arrLines = brdr.lines().toArray();
        brdr.close();

        for(Object o : arrLines){
            String line = o.toString();
            int id = Integer.parseInt(line.substring(0, 8).trim());
            if (maxId < id){
                maxId = id;
            }
        }


        String id = toFixedSizeString(String.valueOf(maxId+1), 8);

        //System.out.println(id + productName + price + quantity);

        BufferedWriter bwrr = new BufferedWriter(new FileWriter(fileName));
        for(Object o : arrLines){
            bwrr.write(o.toString());
            bwrr.newLine();
        }
        bwrr.write(id + productName + price + quantity);
        bwrr.close();
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
