package com.javarush.task.task22.task2212;

/* 
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if (telNumber==null) return false;
        boolean res = true;
        res = res && telNumber.matches("(\\+(\\d\\D?){2})?(\\d\\D?){10}");
        res = res && telNumber.matches(".*(-){0,2}.*") && !telNumber.matches(".*--.*") && !telNumber.matches(".*(-.*){3,}.*");
        res = res && (telNumber.matches(".*(\\(|\\)).*") ? !telNumber.matches(".*-.*(\\(|\\))+.*") && telNumber.matches(".*\\(\\d{3}\\).*") : true);
        res = res && !telNumber.matches(".*[^0-9, ^\\-, ^\\(, ^\\), ^\\+].*");
        res = res && telNumber.matches(".*\\d$");
        return res;
    }

    public static void main(String[] args) {
        System.out.println("news everyone! - " + checkTelNumber(null));

        System.out.println("");
        System.out.println("+380501234567 - " + checkTelNumber("+380501234567"));
        System.out.println("+38(050)1234567 - " + checkTelNumber("+38(050)1234567"));
        System.out.println("+38050123-45-67 - " + checkTelNumber("+38050123-45-67"));
        System.out.println("050123-4567 - " + checkTelNumber("050123-4567"));
        System.out.println("+38)050(1234567 - " + checkTelNumber("+38)050(1234567"));
        System.out.println("+38(050)1-23-45-6-7 - " + checkTelNumber("+38(050)1-23-45-6-7"));
        System.out.println("050ххх4567 - " + checkTelNumber("050ххх4567"));
        System.out.println("050123456 - " + checkTelNumber("050123456"));
        System.out.println("(0)501234567 - " + checkTelNumber("(0)501234567"));
        System.out.println("");

        System.out.println("0501234567 - " + checkTelNumber("0501234567"));
        System.out.println("050-123-4567 - " + checkTelNumber("050-123-4567"));
        System.out.println("050--1234567 - " + checkTelNumber("050--1234567"));
        System.out.println("+3-8(050)1234567 - " + checkTelNumber("+3-8(050)1234567"));
        System.out.println("+38(050)12(345)67 - " + checkTelNumber("+3-8(050)12(345)67"));
    }
}
