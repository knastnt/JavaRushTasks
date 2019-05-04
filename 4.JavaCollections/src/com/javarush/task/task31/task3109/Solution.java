package com.javarush.task.task31.task3109;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/* 
Читаем конфиги
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
        Properties p = new Properties();
        try {
            if (fileName.toLowerCase().endsWith(".xml")) {
                p.loadFromXML(new FileInputStream(fileName));
            }else if (fileName.toLowerCase().endsWith(".txt")) {
                p.load(new FileReader(fileName));
            }else{
                p.load(new FileInputStream(fileName));
            }
        } catch (IOException e) {
            //p = null;
        }
        return p;
    }
}
