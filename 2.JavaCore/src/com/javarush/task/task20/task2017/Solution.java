package com.javarush.task.task20.task2017;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* 
Десериализация
*/
public class Solution {
    public A getOriginalObject(ObjectInputStream objectStream) {

        try {
            A returned = (A)objectStream.readObject();
            return returned;
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("ошибка");
        }
        return null;
    }

    public class A implements Serializable {
    }

    public class B extends A {
        public B() {
            System.out.println("inside B");
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        A a = solution.new A();


    }
}
