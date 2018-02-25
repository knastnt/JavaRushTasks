package com.javarush.task.task14.task1411;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* 
User, Loser, Coder and Proger
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String key = null;
        while (true)
        {
            key = reader.readLine();
            try{
                Class c = Class.forName(Person.class.getPackage().getName() + ".Person$" + firstUpperCase(key));
                Person person = (Person)c.newInstance();
                doWork(person);
            }
            catch (Exception | NoClassDefFoundError e){
                //System.out.println(e);
                break;
            }
        }
    }

    public static void doWork(Person person) {
        // пункт 3
        if (person instanceof Person.User){
            ((Person.User) person).live();
            return;
        }

        Class c = person.getClass();
        Method[] methods = c.getDeclaredMethods();
        try {
            methods[0].invoke(person, null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            //e.printStackTrace();
        }
    }

    public static String firstUpperCase(String string){
        if (string == null || string.isEmpty()) return "";
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }
}
