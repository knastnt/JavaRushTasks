package com.javarush.task.task27.task2712;


import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {
    private List<Tablet> tablets;
    private int interval;

    public RandomOrderGeneratorTask(int interval, List<Tablet> tablets) {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
        while(true) {
            Tablet selectedTablet = tablets.get((int) (Math.random() * tablets.size()));
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
    }
}
