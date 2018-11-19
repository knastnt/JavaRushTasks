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
        tst.put(String.valueOf(Integer.MIN_VALUE), String.valueOf(Byte.MIN_VALUE));
        tst.put("123-to-binary", String.valueOf(Integer.toBinaryString(123)));
        tst.put("123-to-hex", String.valueOf(Integer.toHexString(123)));

        for(Map.Entry<String, String> m : tst.entrySet()){
            System.out.println(m.getKey() + " = " + m.getValue());
        }
    }
}
