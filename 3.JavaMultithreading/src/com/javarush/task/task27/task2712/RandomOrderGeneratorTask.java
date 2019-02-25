package com.javarush.task.task27.task2712;



public class RandomOrderGeneratorTask implements Runnable {
    private Tablet[] tablets;

    public RandomOrderGeneratorTask(Tablet ... tablets) {
        this.tablets = tablets;
    }

    @Override
    public void run() {
        while(true) {
            Tablet selectedTablet = tablets[(int) (Math.random() * tablets.length)];
            try {
                Thread.sleep(Restaurant.getOrderCreatingInterval());
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
    }
}
