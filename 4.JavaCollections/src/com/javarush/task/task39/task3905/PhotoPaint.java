package com.javarush.task.task39.task3905;

public class PhotoPaint {
    public boolean paintFill(Color[][] image, int x, int y, Color desiredColor) {
        if (image == null || desiredColor == null) return false;
        if (x<0 || x>image[0].length-1 || y<0 || y>image.length-1) return false;
        if (image[y][x] == desiredColor) return false;

        Color currentColor = image[y][x];

        paintFill(image, y, x, desiredColor, currentColor);


        return true;
    }



    private void paintFill(Color[][] image, int y, int x, Color desiredColor, Color currentColor) {
        if (x<0 || x>image[0].length-1 || y<0 || y>image.length-1) return;

        if (image[y][x] != currentColor) return;

        image[y][x] = desiredColor;

        paintFill(image, y - 1, x, desiredColor, currentColor);
        paintFill(image, y + 1 , x, desiredColor, currentColor);
        paintFill(image, y, x - 1, desiredColor, currentColor);
        paintFill(image, y, x + 1, desiredColor, currentColor);
    }
}
