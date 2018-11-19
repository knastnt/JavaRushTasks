package com.javarush.task.task23.task2311_IsTheMethodExist;

import java.lang.reflect.Method;

public class test {
    public static class Cat{
        void say(String word){
            System.out.println("Cat says: " + word);
        }
    }

    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.say("hello");

        System.out.println(isHasMethod(cat, "say"));

    }

    static boolean isHasMethod(Object object, String methodName){
        Method[] methods = object.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) return true;
        }
        return false;
    }
}
