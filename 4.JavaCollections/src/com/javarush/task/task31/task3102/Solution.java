package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.*;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List <String> result = new ArrayList<>();
        Stack<File> stack = new Stack<>();
        stack.push(new File(root));

        while (!stack.empty()){
            File curDir = stack.pop();
            if (curDir.listFiles() == null) { continue; }
            for (File file : curDir.listFiles()) {
                if (file.isDirectory()){
                    stack.push(file);
                }else{
                    result.add(file.getAbsolutePath());
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        try {
            List <String> result = getFileTree("D:/");
            for (String s : result) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
