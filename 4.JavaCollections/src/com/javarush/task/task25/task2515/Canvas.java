package com.javarush.task.task25.task2515;
                                                                                                                                                                                                        

                                                                                                                                                                                                        
public class Canvas {
    private int width;
    private int height;
    private char[][] matrix;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        matrix = new char[height][width];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char[][] getMatrix() {
        return matrix;
    }

    public void setPoint(double x, double y, char c){
        int xi = (int) Math.round(x);
        int yi = (int) Math.round(y);
        if(xi < 0 || yi < 0 || xi >= matrix[0].length || yi >= matrix.length){
            return;
        }
        matrix[yi][xi] = c;
    }

    public void drawMatrix(double x, double y, int[][] matrix, char c){
        int xi = (int) Math.round(x);
        int yi = (int) Math.round(y);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) {
                   setPoint(xi+j, yi+i, c);
                }
            }
        }

    }
}