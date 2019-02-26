package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
    private static final int ORDER_CREATING_INTERVAL = 100;

    public static void main(String[] args) {
        Cook cook = new Cook("Amigo");
        cook.setQueue(orderQueue);
        Thread t1 = new Thread(cook);
        t1.setDaemon(true);
        t1.start();

        Cook cook2 = new Cook("Diego");
        cook2.setQueue(orderQueue);
        Thread t2 = new Thread(cook2);
        t2.setDaemon(true);
        t2.start();



        Waiter waiter = new Waiter();


        List<Tablet> tablets = new ArrayList<>();
        for (int i = 0; i < 5 ; i++) {
            tablets.add(new Tablet(i));
            tablets.get(i).setQueue(orderQueue);
        }


        Thread t = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
        t.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
        t.interrupt();



        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
