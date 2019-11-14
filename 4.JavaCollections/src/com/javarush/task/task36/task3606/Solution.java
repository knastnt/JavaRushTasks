package com.javarush.task.task36.task3606;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/* 
Осваиваем ClassLoader и Reflection
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("secondhiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("firsthiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        File file = new File(packageName);
        try {
            Files.walkFileTree(file.toPath(), new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if(file.toString().toUpperCase().endsWith(".CLASS")){
                        System.out.println(file);

                        //Обработка файла класса
                        Class clazz = new ClassLoader(){
                            public Class loadClassFromFile(Path file)  {
                                try {
                                    byte[] b = Files.readAllBytes(file);

                                    Class n = defineClass(null, b, 0, b.length);

                                    return n;
                                } catch ( Throwable x ) {
                                    x.printStackTrace();
                                    return null;
                                }
                            }
                        }.loadClassFromFile(file);

                        if (clazz != null) {
                            try {
                                Constructor constructor = clazz.getDeclaredConstructor();
                                if(HiddenClass.class.isAssignableFrom(clazz)){
                                    hiddenClasses.add(clazz);
                                }
                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                            }
                        }

                        //Обработка файла класса Конец

                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        for (Class hiddenClass : hiddenClasses) {
            if(hiddenClass.getSimpleName().toLowerCase().equals(key.toLowerCase())){
                try {
                    Constructor constructor = hiddenClass.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    return (HiddenClass) constructor.newInstance();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}

