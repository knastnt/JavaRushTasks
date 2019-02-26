package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;

public class Cook extends Observable {
    private String name;
    private volatile boolean busy;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void startCookingOrder(Order order) {
        busy = true;
        ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time " + ((Order)order).getTotalCookingTime() + "min");
        StatisticManager.getInstance().register( new CookedOrderEventDataRow(Tablet.class.getName(), name, ((Order)order).getTotalCookingTime(), ((Order) order).getDishes()));

        try{
            Thread.sleep( ((Order)order).getTotalCookingTime() * 10 );
        }catch (InterruptedException e){
            busy = false;
            return;
        }

        setChanged();
        notifyObservers(order);
        busy = false;
    }

    public boolean isBusy() {
        return busy;
    }
}
