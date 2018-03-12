package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) {
        //add your code here
        try {
            String url = new BufferedReader(new InputStreamReader(System.in)).readLine();
            //url = "http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo";
            url = url.substring(url.indexOf("?")+1);
            String[] urlArr = url.split("&");
            for(String s : urlArr){
                System.out.print(s.split("=")[0] + " ");
            }
            for(String s : urlArr){
                if (s.split("=")[0].equals("obj")){
                    String val = s.split("=")[1];
                    System.out.println("");
                    try{
                        Double dble = Double.parseDouble(val);
                        alert(dble);
                    }catch (Exception e){
                        alert(val);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}
