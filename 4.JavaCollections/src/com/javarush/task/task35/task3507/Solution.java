package com.javarush.task.task35.task3507;

import java.io.*;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> toReturn = new HashSet<>();

        File f = new File(pathToAnimals);
        if(f.isDirectory()){
            for (File file : f.listFiles()) {
                Class clazz = new ClassLoader() {
                    public Class loadClassFromFile(File file)  {
                        try {
                            byte[] b = Files.readAllBytes(file.toPath());

                            Class n = defineClass(null, b, 0, b.length);

                            return n;
                        } catch ( Throwable x ) {
                            x.printStackTrace();
                            return null;
                        }
                    }
                }.loadClassFromFile(file);

                Constructor constructor;
                try {
                    constructor = clazz.getConstructor();
                } catch (NoSuchMethodException e) {
                    constructor = null;
                }
                if(Animal.class.isAssignableFrom(clazz) && constructor != null){
                    try {
                        Animal a = (Animal) clazz.newInstance();
                        toReturn.add(a);
                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return toReturn;
    }
}
