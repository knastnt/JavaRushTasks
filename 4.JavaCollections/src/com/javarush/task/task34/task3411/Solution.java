package com.javarush.task.task34.task3411;

/* 
Ханойские башни
*/

public class Solution {
    public static void main(String[] args) {
        int numRings = 3;
        moveRing('A', 'B', 'C', numRings);
    }

    //Делаем всё с самого глубокого (широкого) диска
    //Допустим, нужно переложить его с первого на второй. Тогда третий будет - для того чтобы освободить дорогу от верхнего.
    public static void moveRing(char a, char b, char c, int numRings) {
        if (numRings==0) return;
        //Освобождаем дорогу - отодвигаем.
        // Убрать numRings-1 с первого на третий
        moveRing( a, c, b, numRings - 1);
        //Перекладываем numRings с первого на второй
        System.out.println("from " + a + " to " + b);
        //Возвращаем на место те, что отодвинули
        // Вернуть numRings-1 с третего на второй
        moveRing(c, b, a, numRings - 1);
    }
}