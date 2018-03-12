package com.javarush.task.task15.task1519;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/* 
Разные методы для разных типов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //напиште тут ваш код
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        String inputted;
        while(true){
            inputted = brdr.readLine();
            if ("exit".equals(inputted)) break;
            if (isDouble(inputted)){
                print(Double.parseDouble(inputted));
                continue;
            }
            if (isShort(inputted)){
                print(Short.parseShort(inputted));
                continue;
            }
            if (isInteger(inputted)){
                print(Integer.parseInt(inputted));
                continue;
            }
            print(inputted);
        }
    }

    private static boolean isInteger(String string){
        try{
            int in = Integer.parseInt(string);
            if (in<=0 || in>=128){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    private static boolean isShort(String string){
        try{
            short sh = Short.parseShort(string);
            if (sh>0 && sh<128){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    private static boolean isDouble(String string){
        if(string.indexOf(".") >= 0){
            try{
                double d = Double.parseDouble(string);
                return true;
            }catch (Exception e){
                return false;
            }
        }
        return false;
    }

    public static void print(Double value) {
        System.out.println("Это тип Double, значение " + value);
    }

    public static void print(String value) {
        System.out.println("Это тип String, значение " + value);
    }

    public static void print(short value) {
        System.out.println("Это тип short, значение " + value);
    }

    public static void print(Integer value) {
        System.out.println("Это тип Integer, значение " + value);
    }
}
