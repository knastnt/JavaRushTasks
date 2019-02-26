package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook implements Runnable {
    private String name;
    private volatile boolean busy;
    private LinkedBlockingQueue<Order> queue;

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

        /*setChanged();
        notifyObservers(order);*/
        try {
            queue.put(order);
        } catch (InterruptedException e) {
            return;
        }
        busy = false;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        /*Thread daemonThread = new Thread(){
            @Override
            public void run() {*/
                while (true){

                    try {

                        /*findCookLabel:
                        while (true){
                            Set<Cook> cooks = StatisticManager.getInstance().getCooks();
                            for (Cook cook : cooks) {*/
                                if (!isBusy()) {
                                    Order order = queue.take();
                                    //new Thread(){
                                    //    @Override
                                    //    public void run() {
                                    startCookingOrder(order);
                                    //    }
                                    //}.start();
                                   // break findCookLabel;
                                }
                            /*}
                            //Thread.sleep(10);
                        }*/



                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            /*}
        };
        daemonThread.setDaemon(true);
        daemonThread.start();*/
    }
}
