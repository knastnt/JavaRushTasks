package com.javarush.task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Thread.sleep;

public class Producer implements Runnable {
    private ConcurrentHashMap<String, String> map;
    private int i=0;

    public Producer(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }

    public void run() {
        Thread currentThread = Thread.currentThread();
        try {
            while (!currentThread.isInterrupted()) {
                i++;
                //System.out.println(i);
                map.put(String.valueOf(i), "Some text for " + i);
                sleep(500);

            }
        }catch (InterruptedException e){
            System.out.println("[" + currentThread.getName() + "] thread was terminated");
        }
    }
}
