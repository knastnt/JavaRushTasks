package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
    }

    public static int getRectangleCount(byte[][] a) {
        int[][] mass = convert(a);
        int x = a.length;
        int y = a[0].length;
        int max = 1;
        for(int i = 0; i < x; i++){
            int current = 0;
            for (int j = 0; j < y; j++) {

                if(mass[i][j] == 0){
                    //это не прямоугольник, сбрасываем current
                    current = 0;
                }else{
                    //Это прямоугольник, mass[i][j] == 1
                    if(current == 0) {
                        if (i != 0) {
                            //Если не первая строка
                            if (mass[i - 1][j] != 0) {
                                current = mass[i - 1][j];
                            } else {
                                current = ++max;
                            }
                        } else {
                            //Если это первая строка
                            current = ++max;
                        }
                    }
                    mass[i][j] = current;
                }
                //System.out.println(mass[i][j]);
            }
        }
        return max - 1;
    }

    private static int[][] convert(byte[][] a){
        int[][] returned = new int[a.length][a[0].length];
        for(int i = 0; i < a.length; i++){
            for (int j = 0; j < a[0].length; j++) {
                returned[i][j] = a[i][j];
            }
        }
        return returned;
    }
}
