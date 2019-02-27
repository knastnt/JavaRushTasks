package com.javarush.task.task30.task3013;

/* 
Набираем код
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int number = Integer.MAX_VALUE - 133;
        System.out.println(Integer.toString(number, 2));

        String result = Integer.toString(solution.resetLowerBits(number), 2);
        System.out.println(result);
    }

    public int resetLowerBits(int number) {
        //напишите тут ваш код

        //дали число, например
        //00000000 00000000 00001000 00000000

        number |= number >> 1;
        //сдвигаем вправо на бит
        //00000000 00000000 00000100 00000000
        //делаем поразрядное ИЛИ и сохраняем
        //00000000 00000000 00001100 00000000

        number |= number >> 2;
        //сдвигаем вправо на 2 бита
        //00000000 00000000 00000011 00000000
        //делаем поразрядное ИЛИ и сохраняем
        //00000000 00000000 00001111 00000000

        number |= number >> 4;
        //сдвигаем вправо на 4 бита
        //00000000 00000000 00000000 11110000
        //делаем поразрядное ИЛИ и сохраняем
        //00000000 00000000 00001111 11110000

        number &= ~number >> 1;
        //сдвигаем вправо на 1 бит и  делаем унарное отрицание
        //00000000 00000000 00000111 11111000
        //11111111 11111111 11111000 00000111
        //делаем поразрядное И с 3 пунктом и сохраняем
        //00000000 00000000 00001000 00000000
        return number;
    }
}