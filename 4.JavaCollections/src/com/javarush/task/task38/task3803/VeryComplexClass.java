package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object s = "str";
        Long l = ((Long)s);
    }

    public void methodThrowsNullPointerException() {
        int[] i = new int[1];
        i=null;
        int j = i[2];
    }

    public static void main(String[] args) {

    }
}
