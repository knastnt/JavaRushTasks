package com.javarush.task.task36.task3602;

import sun.reflect.Reflection;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.*;

/* 
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class[] classes = Collections.class.getDeclaredClasses();
        for (Class classesOne : classes) {
            Class r = reScan(classesOne);
            if(r != null) return r;
        }
        return null;

        //return reScan(Collections.class);
    }

    public static Class reScan(Class clazz){
//        System.out.println(prefix + clazz);

        if(List.class.isAssignableFrom(clazz) || List.class.isAssignableFrom(clazz.getSuperclass())){
            if(Modifier.isPrivate(clazz.getModifiers())){
                if(Modifier.isStatic(clazz.getModifiers())){
                    try {
                        Method method = clazz.getDeclaredMethod("get", int.class);
                        method.setAccessible(true);
                        Constructor constructor = clazz.getDeclaredConstructor();
                        constructor.setAccessible(true);

                        method.invoke(constructor.newInstance(), 0);
                    } catch (IndexOutOfBoundsException e) {
//                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
//                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
                    } catch (InstantiationException e) {
//                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        if(e.getTargetException() instanceof IndexOutOfBoundsException) {
                            //System.out.println(prefix + clazz);
                            return clazz;
                        }
//                        e.printStackTrace();
                    }
                }
            }
        }

        Class[] classes = clazz.getDeclaredClasses();
        for (Class classesOne : classes) {
            Class r = reScan(classesOne);
            if(r != null) return r;
        }
        return null;
    }

}
