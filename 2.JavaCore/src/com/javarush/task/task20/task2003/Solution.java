package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.*;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();
    public static String fileName;

    public void fillInPropertiesMap() throws Exception{
        //implement this method - реализуйте этот метод
        BufferedReader brdr=new BufferedReader(new InputStreamReader(System.in));
        fileName=brdr.readLine();
        brdr.close();
        
        FileInputStream fis = new FileInputStream(fileName);
        load(fis);
        fis.close();
    }

    public void save(OutputStream outputStream) throws Exception {
        Properties prop = new Properties();
        prop.putAll(properties);
        prop.store(outputStream, null);
    }

    public void load(InputStream inputStream) throws Exception {
        Properties prop = new Properties();
        prop.load(inputStream);
        properties.putAll((Map)prop);
    }

    public static void main(String[] args) throws Exception {
        Solution s = new Solution();
        s.fillInPropertiesMap();
        s.save(new FileOutputStream(fileName));
    }
}
