package com.javarush.task.task25.task2512;

import java.util.ArrayList;

/*
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        //System.out.println("---");
        ArrayList<Throwable> le = new ArrayList<>();
        le.add(e);
        while(le.get(le.size()-1).getCause() != null){
            le.add(le.get(le.size()-1).getCause());
        }
        for (int i = le.size() - 1; i >= 0; i--) {
            System.out.println(le.get(i));
        }
    }

    public static void main(String[] args) {
        /*Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1000);
                    throw new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")));
                }catch (Exception e){
                    ArrayList<Throwable> le = new ArrayList<>();
                    le.add(e);
                    while(le.get(le.size()-1).getCause() != null){
                        le.add(le.get(le.size()-1).getCause());
                    }
                    for (int i = le.size() - 1; i >= 0; i--) {
                        System.out.println(le.get(i));
                    }
                }
            }
        };
        t.setUncaughtExceptionHandler(new Solution());
        t.start();*/

    }
}
