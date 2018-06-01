package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread{
    private Thread thread;
    private State old_state = null;

    public LoggingStateThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        while (thread.getState() != State.TERMINATED) {
            if (old_state != thread.getState()) {
                old_state = thread.getState();
                System.out.println(thread.getState().toString());
            }
                /*try {
                    sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
        }
    }
}
