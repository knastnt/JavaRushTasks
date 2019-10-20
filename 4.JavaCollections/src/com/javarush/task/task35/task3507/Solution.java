package com.javarush.task.task35.task3507;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
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
        Set<? extends Animal> toReturn = new HashSet<>();

        if(!pathToAnimals.endsWith("/")){
            pathToAnimals += "/";
        }
        /**
         * Создаем загрузчик модулей.
         */
        ModuleLoader loader = new ModuleLoader(pathToAnimals, ClassLoader.getSystemClassLoader());
//        File f = new File(pathToAnimals);
//        if(f.isDirectory()){
//            for (File file : f.listFiles()) {
//
//                String absolutePath = file.getAbsolutePath();
//                String fileName = file.getName();
//                try {
//                    URL[] urls = {new URL("file:" + pathToAnimals)};
//                    ClassLoader classLoader = new URLClassLoader(urls);
//                    Class clazz = Class.forName(fileName, true, classLoader);
//                } catch (ClassNotFoundException | MalformedURLException e) {
//                    System.out.println("Error while loading " + file.getAbsolutePath());
//                    System.out.println(e);
//                }
//
//            }
//        }

        File f = new File(pathToAnimals);
        String[] modules = f.list();
        if(f.isDirectory()){
            for (File file : f.listFiles()) {
//                String className = file.getName().replaceFirst("[.][^.]+$", "");
//                try {
//                    byte[] buffer = Files.readAllBytes(file.toPath());
//                    ClassLoader classLoader = new ClassLoader() {
//                        @Override
//                        protected Class<?> findClass(String name) throws ClassNotFoundException {
//                            return defineClass(name, buffer, 0, buffer.length);
//                        }
//                    };
//                    Class clazz = classLoader.loadClass(className);
//
//
//
//
//                            Class clazz = loader.loadClass(className);
//                            Object execute = clazz.newInstance();
//
//
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (InstantiationException e) {
//                    e.printStackTrace();
//                }





            }
        }

//        if (!pathToAnimals.endsWith("/")){
//            pathToAnimals += "/";
//        }
//
//        try{
//            URL[] urls = {new URL("file:" + pathToAnimals)};
//            ClassLoader classLoader = new URLClassLoader(urls);
//
//            Class clazz = classLoader.loadClass("Cat");
//        }catch (Exception e){
//            e.printStackTrace();
//        }


        return toReturn;
    }




    public static class ModuleLoader extends ClassLoader {

        /**
         * Путь до директории с модулями.
         */
        private String pathtobin;

        public ModuleLoader(String pathtobin, ClassLoader parent) {
            super(parent);
            this.pathtobin = pathtobin;
        }

        @Override
        public Class<?> findClass(String className) throws ClassNotFoundException {
            try {
                /**
                 * Получем байт-код из файла и загружаем класс в рантайм
                 */
                byte b[] = fetchClassFromFS(pathtobin + className + ".class");
                return defineClass(className, b, 0, b.length);
            } catch (FileNotFoundException ex) {
                return super.findClass(className);
            } catch (IOException ex) {
                return super.findClass(className);
            }

        }

        /**
         * Взято из www.java-tips.org/java-se-tips/java.io/reading-a-file-into-a-byte-array.html
         */
        private byte[] fetchClassFromFS(String path) throws FileNotFoundException, IOException {
            InputStream is = new FileInputStream(new File(path));

            // Get the size of the file
            long length = new File(path).length();

            if (length > Integer.MAX_VALUE) {
                // File is too large
            }

            // Create the byte array to hold the data
            byte[] bytes = new byte[(int)length];

            // Read in the bytes
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length
                    && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
                offset += numRead;
            }

            // Ensure all the bytes have been read in
            if (offset < bytes.length) {
                throw new IOException("Could not completely read file "+path);
            }

            // Close the input stream and return bytes
            is.close();
            return bytes;

        }
    }
}
