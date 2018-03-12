package com.javarush.task.task18.task1818;

/* 
Два в одном
*/
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException{
    BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
String f1=brdr.readLine();
String f2=brdr.readLine();
String f3=brdr.readLine();
brdr.close();

FileInputStream fis = new FileInputStream(f2);
FileInputStream fis2= new FileInputStream(f3);
FileOutputStream fos = new FileOutputStream(f1);

while(fis.available()>0){
    fos.write(fis.read());
}
while(fis2.available()>0){
    fos.write(fis2.read());
}
fos.close();
fis.close();
fis2.close();
    }
}
