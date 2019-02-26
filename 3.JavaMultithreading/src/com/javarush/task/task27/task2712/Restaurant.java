package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;

    public static void main(String[] args) {
        OrderManager orderManager = new OrderManager();

        Cook cook = new Cook("Amigo");
        Cook cook2 = new Cook("Diego");


        Waiter waiter = new Waiter();


        //Tablet tablet = new Tablet(1);
        List<Tablet> tablets = new ArrayList<>();
        for (int i = 0; i < 5 ; i++) {
            tablets.add(new Tablet(i));
            tablets.get(i).addObserver(orderManager);
        }



        //tablet.addObserver(cook);
        cook.addObserver(waiter);
        cook2.addObserver(waiter);

        /*tablet.createOrder();
        tablet.createOrder();
        tablet.createOrder();
        tablet.createOrder();*/

        Thread t = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
        t.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
        t.interrupt();


        StatisticManager.getInstance().register(cook);
        StatisticManager.getInstance().register(cook2);

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
