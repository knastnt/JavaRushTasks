package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
Проход по дереву файлов
*/
public class Solution {
    private static ArrayList<File> list = new ArrayList<>();

    public static void main(String[] args) {
        String path = args.length>0 ? args[0] : "D:\\";
        String resultFileAbsolutePath = args.length>1 ? args[1] : "D:\\result.txt";
        File resultFile = new File(resultFileAbsolutePath);

        printFiles(new File(path));

        Collections.sort(list, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
            }
        });

        /*for (File file : list) {
            System.out.println(file.getName());
        }*/

        if (FileUtils.isExist(resultFile)){
            //System.out.println("success");
            File renamed = new File(resultFile.getParent() + "/allFilesContent.txt");
            FileUtils.renameFile(resultFile, renamed);

            try {
                OutputStreamWriter osw = new FileWriter(renamed);
                for (File file : list) {
                    try {
                        InputStreamReader isr = new FileReader(file);
                        while (isr.ready()) {
                            osw.write(isr.read());
                        }
                        isr.close();
                        osw.write('\n');
                    }catch (IOException e){
                        System.out.println("I/O Error on file " + file.getAbsolutePath());
                    }
                }
                osw.close();
                System.out.println("success");
            }catch (IOException e){
                System.out.println("I/O Error");
            }
        }else{
            System.out.println("no file");
        }
    }

    private static void printFiles(File f){
        if (f.listFiles() == null) return;
        for (File file : f.listFiles())
        {
            if (file.isDirectory()){
                printFiles(file);
            }else{
                if (file.length() <= 50) {
                    list.add(file);
                }
            }
        }
    }
}
