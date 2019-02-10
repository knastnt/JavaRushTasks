package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.kitchen.Order;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet extends Observable {
    final int number;
    static Logger logger = Logger.getLogger(Tablet.class.getName());

    public Tablet(int number) {
        this.number = number;
    }

    public Order createOrder(){
        try {
            Order order = new Order(this);
            if (order.isEmpty()) return null;
            ConsoleHelper.writeMessage(order.toString());
            setChanged();
            notifyObservers(order);
            //new Thread(){
            //    @Override
            //    public void run() {
                    new AdvertisementManager(order.getTotalCookingTime() * 60).processVideos();
            //    }
            //}.start();
            return order;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
            return null;
        }
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Tablet{");
        sb.append("number=").append(number);
        sb.append('}');
        return sb.toString();
    }


}
