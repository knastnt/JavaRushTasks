package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.NoAvailableVideoEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet {
    final int number;
    static Logger logger = Logger.getLogger(Tablet.class.getName());
    private LinkedBlockingQueue<Order> queue;

    public Tablet(int number) {
        this.number = number;
    }

    public Order createOrder(){
        try {
            Order order = new Order(this);
            return processOfCreatingOrder(order);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
            return null;
        }
    }

    private Order processOfCreatingOrder(Order order) {
            if (order.isEmpty()) return null;
            ConsoleHelper.writeMessage(order.toString());

            int totalDuration = order.getTotalCookingTime() * 60;
            try {
                new AdvertisementManager(totalDuration).processVideos();
                ConsoleHelper.writeMessage("видео выбрано");
            } catch (NoVideoAvailableException e) {
                logger.log(Level.INFO, "No video is available for the order " + order);
                StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(totalDuration));
            }
        try {
            queue.put(order);
        } catch (InterruptedException e) {
            return null;
        }
        return order;
    }

    public void createTestOrder(){
        try {
            Order order = new TestOrder(this);
            processOfCreatingOrder(order);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
            //return null;
        }
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Tablet{");
        sb.append("number=").append(number);
        sb.append('}');
        return sb.toString();
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }
}
