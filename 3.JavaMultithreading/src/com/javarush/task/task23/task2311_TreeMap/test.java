package com.javarush.task.task23.task2311_TreeMap;

import java.util.Map;
import java.util.TreeMap;

public class test {
    public static void main(String[] args) {
        Map<String, String> tst = new TreeMap<String, String>();

        tst.put("Один", "Два");
        tst.put("Три", "Четыре");
        tst.put("5", "Шесть");
        tst.put("seven", "8");
        tst.put("Девять", "ten");

        for(Map.Entry<String, String> m : tst.entrySet()){
            System.out.println(m.getKey() + " = " + m.getValue());
        }
    }
}
