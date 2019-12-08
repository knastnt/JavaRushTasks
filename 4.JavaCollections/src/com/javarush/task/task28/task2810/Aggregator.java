package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.HHStrategy;
import com.javarush.task.task28.task2810.model.Provider;

public class Aggregator {
    public static void main(String[] args) {
        try {
            Controller controller = new Controller(new Provider(new HHStrategy()));
            controller.scan();
        }catch (NullPointerException e){

        }
    }
}
