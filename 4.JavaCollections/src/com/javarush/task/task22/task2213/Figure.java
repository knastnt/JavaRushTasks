package com.javarush.task.task22.task2213;

public class Figure {
    private int x;
    private int y;
    private int[][] matrix;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void left() { //для движения фигурки влево.

        if (isCurrentPositionAvailable()) { x -= 1; }
    }
    public void right() { //для движения фигурки вправо.

        if (isCurrentPositionAvailable()) { x += 1; }
    }
    public void down() { //для движения фигурки вниз.
        y += 1;
    }
    public void up() { //для движения фигурки вверх.
        y -= 1;
    }
    public void rotate() { //для поворота фигурки вокруг главной диагонали.

    }
    public void downMaximum() { //падение фигурки в низ до дна.

    }
    public boolean isCurrentPositionAvailable() { //проверка - может ли фигурка быть помещена в текущую позицию. Для теста захардкодь результат в true, пока мы не реализовали логику.
        return true;
    }
    public void landed() { //вызывается, когда фигурка достигла дна или уперлась в другую фигурку. Все ее занятые клетки теперь должны добавиться в Field.

    }


    public Figure(int x, int y, int[][] matrix) {
        this.x = x;
        this.y = y;
        this.matrix = matrix;
    }
}
