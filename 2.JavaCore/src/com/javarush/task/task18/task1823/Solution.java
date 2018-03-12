package com.javarush.task.task18.task1823;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        String file;
        while(true){
            file = brdr.readLine();
            if ("exit".equals(file)){
                break;
            }
            new ReadThread(file).start();
        }
        brdr.close();
    }

    public static class ReadThread extends Thread {
        private String  file;
        public ReadThread(String fileName) {
            //implement constructor body
            file = fileName;
        }
        // implement file reading here - реализуйте чтение из файла тут

        @Override
        public void run() {
            Map<Integer, Integer> bytesMap = new HashMap<Integer, Integer>();
            try {
                FileInputStream fis = new FileInputStream(file);
                while (fis.available()>0){
                    int currentByte = fis.read();
                    if(bytesMap.containsKey(currentByte)){
                        bytesMap.replace(currentByte, bytesMap.get(currentByte).intValue()+1);
                    }else{
                        bytesMap.put(currentByte,1);
                    }
                }
                fis.close();
                int maxCount=0;
                int byteMaxCount = -1;
                for(Map.Entry<Integer, Integer> es : bytesMap.entrySet()){
                    if(es.getValue().intValue() > maxCount){
                        maxCount = es.getValue().intValue();
                        byteMaxCount = es.getKey().intValue();
                    }
                }
                if(byteMaxCount != -1) {
                    resultMap.put(file, byteMaxCount);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
