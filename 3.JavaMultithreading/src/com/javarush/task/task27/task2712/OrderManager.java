package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderManager implements Observer {
    private LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public OrderManager() {
        Thread daemonThread = new Thread(){
            @Override
            public void run() {
                while (true){

                    try {

                        findCookLabel:
                        while (true){
                            Set<Cook> cooks = StatisticManager.getInstance().getCooks();
                            for (Cook cook : cooks) {
                                if (!cook.isBusy()) {
                                        Order order = orderQueue.take();
                                        new Thread(){
                                            @Override
                                            public void run() {
                                                cook.startCookingOrder(order);
                                            }
                                        }.start();
                                        break findCookLabel;
                                }
                            }
                            //Thread.sleep(10);
                        }



                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        };
        daemonThread.setDaemon(true);
        daemonThread.start();
    }

    @Override
    public void update(Observable o, Object order) {
        try {
            orderQueue.put((Order) order);
        } catch (InterruptedException e) {
            return;
        }
    }
}
