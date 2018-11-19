package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread{
    private Thread thread;
    private State old_state = null;

    public LoggingStateThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {


        while(true) {
            if (old_state != thread.getState()) {
                old_state = thread.getState();
                System.out.println(thread.getState().toString());
            }
            if (old_state == State.TERMINATED) break;
        }

    }
}
