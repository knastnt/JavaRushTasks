package com.javarush.task.task22.task2213;

public class Field {
    private int width;
    private int height;
    private int[][] matrix;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void print() { //объект будет отрисовывать на экран свое текущее состояние;
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if (matrix[y][x]==0) {
                    System.out.print(".");
                }else{
                    System.out.print("X");
                }
            }
            System.out.println("");
        }
    }
    public void removeFullLines() { //будет удалять из матрицы полностью заполненные строки и сдвигать вышележащие строки вниз;

    }
    public Integer getValue(int x, int y) { //возвращает значение которое находится в матрице с координатами x и y;
        return null;
    }
    public void setValue(int x, int y, int value) { //устанавливает переданное значение в ячейку массива (матрицы) с координатами x, y.

    }

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        matrix = new int[height][width];
    }
}
