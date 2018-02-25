package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    static {
        threads.add(new one());
        threads.add(new two());
        threads.add(new three());
        threads.add(new four());
        threads.add(new five());
    }

    public static void main(String[] args) {
        for (Thread t : threads) {
            t.start();
        }
    }

    public static class one extends Thread {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {

                }
            }
        }
    }

    public static class two extends Thread {
        public void run() {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println("InterruptedException");
            }
        }
    }

    public static class three extends Thread {
        public void run() {
            while (true) {
                try {

                    System.out.println("Ура");
                    sleep(500);
                } catch (Exception e) {
                }
            }
        }
    }

    public static class four extends Thread implements Message {
        public void run() {

            try {
                while (true) {
                    Thread.sleep(500);
                }
            } catch (Exception e) {
            }

        }

        public void showWarning() {
            if (isAlive()) {
                interrupt();
            }
        }
    }

    public static class five extends Thread {
        public void run() {
            BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));

            int sum = 0;
            while (true) {
                String readed = "";
                try {
                    readed = brdr.readLine();
                } catch (Exception e) {

                }
                if ("N".equals(readed)) {
                    System.out.println(sum);
                    break;
                } else {
                    try {
                        sum = sum + Integer.parseInt(readed);
                    } catch (Exception e) {

                    }
                }

            }

            try {
                brdr.close();
            } catch (Exception e) {

            }
        }
    }
}