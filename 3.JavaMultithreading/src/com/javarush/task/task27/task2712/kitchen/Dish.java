package com.javarush.task.task27.task2712.kitchen;

public enum Dish {
    Fish, Steak, Soup, Juice, Water;

    public static String allDishesToString(){
        final StringBuffer sb = new StringBuffer();
        for(Dish s : values()){
            sb.append(", " + s );
        }
        if(sb.length() > 0) sb.delete(0,2);
        return sb.toString();
    }
}
