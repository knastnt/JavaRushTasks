package com.javarush.task.task18.task1825;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {

        //System.out.println(getPart("Lion.avi.part1"));
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        while(true){
            String readed = brdr.readLine();
            if("end".equals(readed)){
                break;
            }
            int part = getPart(readed);
            map.put(part,readed);
        }
        Integer[] mapInt = map.keySet().toArray(new Integer[0]);
        Arrays.sort(mapInt);

        String fn = getName(map.get(1).toString());

        FileOutputStream fos = new FileOutputStream(fn);


        for (int i : mapInt){
            FileInputStream fis = new FileInputStream(map.get(i).toString());
            byte[] buff = new byte[100];
            int cnt = fis.read(buff);
            fos.write(buff, 0, cnt);

            fis.close();
        }
        fos.close();


    }

    public static int getPart(String testString) {
        Pattern p = Pattern.compile(".+\\.part(\\d+)$");
        Matcher m = p.matcher(testString);
        //return m.matches();
        m.find();
        return Integer.parseInt(m.group(1));
    }
    public static String getName(String testString) {
        Pattern p = Pattern.compile("(.+)\\.part(\\d+)$");
        Matcher m = p.matcher(testString);
        //return m.matches();
        m.find();
        return m.group(1);
    }
}