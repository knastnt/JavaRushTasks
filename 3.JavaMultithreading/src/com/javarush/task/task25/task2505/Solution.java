package com.javarush.task.task25.task2505;

import static java.lang.Thread.sleep;

/*
Без дураков
*/
public class Solution {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new Solution().new MyThread("super secret key");
        myThread.start();
       // sleep(600);
    }

    public class MyThread extends Thread {
        private String secretKey;

        public MyThread(String secretKey) throws InterruptedException {
            this.secretKey = secretKey;
            setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
            //setDaemon(true);
        }

        @Override
        public void run() {
            throw new NullPointerException("it's an example");
        }

        private class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                try {
                    sleep(500);
                    if(t instanceof MyThread) {
                        System.out.println(String.format("%s, %s, %s",((MyThread) t).secretKey, t.getName(), e.getMessage()));
                    }

                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

            }
        }
    }


}

