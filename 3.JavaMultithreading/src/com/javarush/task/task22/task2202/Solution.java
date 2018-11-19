package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
        System.out.println(getPartOfString("Амиго и Диего лучшие друзья!"));
    }

    public static String getPartOfString(String string) throws TooShortStringException {
        StringBuffer sb = new StringBuffer();
        try{

            String[] arr = string.split(" ");

            for(int i=1; i<=4; i++){
                sb.append(arr[i] + " ");
            }


        }catch (Exception e){
            throw new TooShortStringException();
        }
        return sb.toString().trim();
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
