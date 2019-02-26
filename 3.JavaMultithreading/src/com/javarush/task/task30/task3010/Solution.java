package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        int minRadix = 0;
        try {
            for (int i = 36; i >= 2; i--) {
                BigInteger bigInteger = new BigInteger(args[0], i);
                minRadix = i;
            }
        }
        catch (NumberFormatException e){}
        catch (Exception e){ return; }
        System.out.println(minRadix == 0 ? "incorrect" : minRadix);
    }
}