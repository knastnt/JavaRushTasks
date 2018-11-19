package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {
    private static volatile Integer counter = 0;

    private int getNewPriority(){
        int newPriority;

        synchronized (counter) {
            newPriority = ++counter;
            if (newPriority > 10) {
                counter = 1;
                newPriority = 1;
            }
        }

        if(Thread.currentThread().getThreadGroup().getMaxPriority() < newPriority) newPriority = Thread.currentThread().getThreadGroup().getMaxPriority();

        return newPriority;
    }


    public MyThread() {
        this.setPriority(getNewPriority());
    }

    public MyThread(Runnable target) {
        super(target);
        this.setPriority(getNewPriority());
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        this.setPriority(getNewPriority());
    }

    public MyThread(String name) {
        super(name);
        this.setPriority(getNewPriority());
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        this.setPriority(getNewPriority());
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        this.setPriority(getNewPriority());
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        this.setPriority(getNewPriority());
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        this.setPriority(getNewPriority());
    }
}
