package com.javarush.task.task18.task1808;

/* 
Разделение файла
*/
import java.io.*;

public class Solution {
    public static void main(String[] args) {
        try{
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader brdr = new BufferedReader(isr);
        String f1 = brdr.readLine();
        String f2 = brdr.readLine();
        String f3 = brdr.readLine();
        brdr.close();
        isr.close();
        
        FileInputStream fis = new FileInputStream(f1);
        FileOutputStream fos1= new FileOutputStream(f2);
        FileOutputStream fos2= new FileOutputStream(f3);



        int firstPartBytes = fis.available()/2 + fis.available()%2;
        int current=0;
        while(fis.available()>0){
            int b = fis.read();
            current++;
            if(current<=firstPartBytes){
                fos1.write(b);
            }else{
                fos2.write(b);
            }
        }
        fis.close();
        fos1.close();
        fos2.close();
    
        }catch(Exception e){
            
        }
    
    }
}
