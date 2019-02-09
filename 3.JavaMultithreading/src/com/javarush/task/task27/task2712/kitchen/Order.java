package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        dishes = ConsoleHelper.getAllDishesForOrder();
    }


    @Override
    public String toString() {
        if(dishes.size() == 0) return "";

        final StringBuffer sb = new StringBuffer("Your order: [");
        sb.append(allDishesToString());
        sb.append("] of ");
        sb.append(tablet.toString());
        return sb.toString();
    }

    public String allDishesToString(){
        final StringBuffer sb = new StringBuffer();
        for(Dish s : dishes){
            sb.append(", " + s );
        }
        if(sb.length() > 0) sb.delete(0,2);
        return sb.toString();
    }

    public int getTotalCookingTime(){
        int duration = 0;
        for(Dish dish : dishes){
            duration += dish.getDuration();
        }
        return duration;
    }

    public boolean isEmpty(){
        return dishes.size() == 0;
    }
}
