package com.javarush.task.task27.task2707;

import static java.lang.Thread.enumerate;
import static java.lang.Thread.sleep;

/*
Определяем порядок захвата монитора
*/
public class Solution {
    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        synchronized (obj1) {
            synchronized (obj2) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    public static boolean isNormalLockOrder(final Solution solution, final Object o1, final Object o2) throws Exception {
        //do something here
        Thread t1 = null;
        Thread t2 = null;
        for(int i = 0; i < 20; i++){
            t1 = new Thread(){
                @Override
                public void run() {
                    for (int j = 0; j < 20; j++) {
                        solution.someMethodWithSynchronizedBlocks(o1, o2);
                    }
                }
            };
            t1.start();

            t2 = new Thread(){
                @Override
                public void run() {
                    for (int j = 0; j < 20; j++) {
                        synchronized (o1) {
                            synchronized (o2) {
                                System.out.println("my_thread_" + o1 + " " + o2);
                            }
                        }
                    }
                }
            };
            t2.start();
        }
        sleep(1000);
        return !(t1.getState() == Thread.State.BLOCKED && t2.getState() == Thread.State.BLOCKED);
        //return false;
    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        System.out.println(isNormalLockOrder(solution, o1, o2));
    }
}
