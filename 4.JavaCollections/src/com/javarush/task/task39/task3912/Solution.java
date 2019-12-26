package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

import java.util.Random;

public class Solution {
    public static void main(String[] args) {
//        int[][] matrix =
//                {
//                        {1, 0, 0, 1, 1, 0, 1, 1, 0, 0, },
//                        {0, 0, 0, 0, 0, 0, 1, 1, 0, 1, },
//                        {0, 1, 0, 1, 1, 1, 0, 0, 0, 1, },
//                        {1, 1, 0, 1, 1, 0, 0, 0, 1, 0, },
//                        {1, 1, 1, 1, 1, 1, 0, 1, 0, 0, },
//                        {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, },
//                        {1, 1, 1, 0, 0, 1, 1, 1, 1, 0, },
//                        {1, 0, 1, 0, 1, 0, 0, 0, 0, 1, },
//                        {0, 0, 0, 1, 0, 1, 0, 0, 0, 1, },
//                        {0, 1, 1, 0, 0, 0, 0, 0, 1, 0, },
//                };
        int[][] matrix = new int[10][10];
        Random r = new Random();
        for (int i=0; i<matrix.length; i++){
            System.out.print("{");
            for (int j=0; j<matrix[i].length; j++){
                matrix[i][j] = r.nextInt(2);
                System.out.print(matrix[i][j] + ", ");
            }
            System.out.println("},");
        }

        System.out.println(maxSquare(matrix));
    }

    public static int maxSquare(int[][] matrix) {
        int max = 0;
        for (int i=0; i<matrix.length; i++){
            for (int j=0; j<matrix[i].length; j++){
                int s = getDeeper(i, j, 0, matrix);
                if (max < s) max = s;
            }
        }
        return max;
    }

    private static int getDeeper(int i, int j, int deep, int[][] matrix){
        try {
            if (matrix[i+deep][j+deep] == 0) return 0;

            for (int i1 = i; i1 <= i + deep; i1++) {
                if (matrix[i1][j+deep] == 0) return 0;
            }
            for (int j1 = j; j1 <= j + deep; j1++) {
                if (matrix[i+deep][j1] == 0) return 0;
            }

            int s = deep * 2 + 1;
            s = s + getDeeper(i, j, deep + 1, matrix);
            return s;
        }catch (ArrayIndexOutOfBoundsException e){
            return 0;
        }
    }
}
