package com.javarush.task.task19.task1906;

/* 
Четные символы
*/
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        String file1 = brdr.readLine();
        String file2 = brdr.readLine();
        brdr.close();
        
        FileReader fis = new FileReader(file1);
        FileWriter fos = new FileWriter(file2);
        boolean ch=false;
        while(fis.ready()){
            if(ch){
                fos.write(fis.read());
            }else{
                fis.skip(1);
            }
            ch = !ch;
        }
        fos.flush();
        fis.close();
        fos.close();
    }
}
