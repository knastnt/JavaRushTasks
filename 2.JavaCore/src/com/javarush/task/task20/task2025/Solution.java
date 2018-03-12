package com.javarush.task.task20.task2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Алгоритмы-числа
*/
public class Solution {
    public static boolean showInfoNow = false;

    public static long[] getNumbers(long N) {
        ArrayList<Long> resArr = new ArrayList<Long>();

        generator gen = new generator(N);
        match.init(N);

        while(gen.generateNext()) {
            //System.out.println(gen.getNumber());

            long tested = match.testArmstrong(gen);

            //System.out.println(tested);

            if(tested>0 && tested < N){
                resArr.add(tested);
            }
        }

        long[] result = new long[resArr.size()];
        for (int i = 0; i < resArr.size(); i++) {
            result[i] = resArr.get(i);
        }
        Arrays.sort(result);
        return result;
    }






    public static void main(String[] args) {
        //timer r = new timer();
        //Thread t = new Thread(r);
        //t.start();

        long a = System.currentTimeMillis();
        long[] y = getNumbers(Long.MAX_VALUE);
        long b = System.currentTimeMillis();
        System.out.println("time seconds "+(b-a)/1000);
        System.out.println("memory "+(Runtime.getRuntime().totalMemory()
                - Runtime.getRuntime().freeMemory())/1024/1024 + " mb");
        for (int i = 0; i < y.length; i++) {
            System.out.println(i + " = " + y[i]);
        }

        //t.interrupt();
    }


    public static class timer implements Runnable{
        @Override
        public void run() {
            try {
                while (true){
                    Thread.sleep(1000);
                    showInfoNow = true;
                }
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
    }


}


