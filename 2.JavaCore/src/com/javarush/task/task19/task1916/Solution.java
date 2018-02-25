package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        String file1 = brdr.readLine();
        String file2 = brdr.readLine();
        brdr.close();

        BufferedReader brdr1 = new BufferedReader(new FileReader(file1));
        BufferedReader brdr2 = new BufferedReader(new FileReader(file2));

        Object[] file1arr = brdr1.lines().toArray();
        Object[] file2arr = brdr2.lines().toArray();

        brdr1.close();
        brdr2.close();

        int i=0, j=0;

        try{
        while(true){
            if(file1arr[i].toString().equals(file2arr[j].toString())){
                //System.out.println("SAME " + file1arr[i].toString());
                lines.add(new LineItem(Type.SAME, file1arr[i].toString()));
                i++;
                j++;
                continue;
            }else{
                if(file1arr[i+1].toString().equals(file2arr[j].toString())){
                    //System.out.println("DEL " + file1arr[i].toString());
                    lines.add(new LineItem(Type.REMOVED, file1arr[i].toString()));
                    i++;
                    continue;
                }
                if(file1arr[i].toString().equals(file2arr[j+1].toString())){
                    //System.out.println("ADD " + file2arr[j].toString());
                    lines.add(new LineItem(Type.ADDED, file2arr[j].toString()));
                    j++;
                    continue;
                }
            }
        }
        }catch (ArrayIndexOutOfBoundsException e){
            //System.out.println(file1arr.length + "  " + i);
            //System.out.println(file2arr.length + "  " + j);
            for(;i<file1arr.length;i++){
                //System.out.println("DEL " + file1arr[i].toString());
                lines.add(new LineItem(Type.REMOVED, file1arr[i].toString()));
            }
            for(;j<file2arr.length;j++){
                //System.out.println("ADD " + file2arr[j].toString());
                lines.add(new LineItem(Type.ADDED, file2arr[j].toString()));
            }
        }

    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
