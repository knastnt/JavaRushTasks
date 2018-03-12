package com.javarush.task.task16.task1630;

import java.io.*;

public class Solution {
    public static String firstFileName;
    public static String secondFileName;

    //add your code here - добавьте код тут
    static{
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        try {
            firstFileName = brdr.readLine();
            secondFileName = brdr.readLine();
            brdr.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        f.join();
        //add your code here - добавьте код тут
        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }

    //add your code here - добавьте код тут
    public static class ReadFileThread extends Thread implements ReadFileInterface{
        private String fullFileName;
        private String fullFileContent;

        @Override
        public void setFileName(String fullFileName) {
            this.fullFileName = fullFileName;
            this.fullFileContent = "";
        }

        @Override
        public String getFileContent() {
            return fullFileContent;
        }

        @Override
        public void run() {
            try {
                BufferedReader brdr = new BufferedReader(new InputStreamReader((new FileInputStream(fullFileName))));
                StringBuffer fileContent = new StringBuffer();
                while(true){
                    String readed = brdr.readLine();
                    if (readed == null){
                        break;
                    }
                    fileContent.append(" ");
                    fileContent.append(readed);
                }
                brdr.close();
                fullFileContent = fileContent.toString();
            } catch (Exception e) {
                e.printStackTrace();
                fullFileContent = "";
            }
        }
    }
}
