package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

public class DirectorTablet {
    public void printAdvertisementProfit() {
        Double total = 0.0;
        SortedMap<Date, Double> profits = StatisticManager.getInstance().getAdvertisementProfit();

        for (Map.Entry<Date, Double> e : profits.entrySet()){
            ConsoleHelper.writeMessage( new SimpleDateFormat("dd-MMM-yyyy").format(e.getKey()) + " - " + String.format("%.2f", e.getValue()) );
            total += e.getValue();
        }

        ConsoleHelper.writeMessage("Total - " + String.format("%.2f", total));
    }

    public void printCookWorkloading() {
        SortedMap<Date, SortedMap<String, Integer>> workloading = StatisticManager.getInstance().getCookWorkloading();

        for (Map.Entry<Date, SortedMap<String, Integer>> e : workloading.entrySet()){
            ConsoleHelper.writeMessage( new SimpleDateFormat("dd-MMM-yyyy").format(e.getKey()) );
            for (Map.Entry<String, Integer> e2 : e.getValue().entrySet()){
                ConsoleHelper.writeMessage( e2.getKey() + " - " + (int)Math.ceil(e2.getValue()) + " min" );
            }
        }

        ConsoleHelper.writeMessage( "" );
    }

    public void printActiveVideoSet() {

    }

    public void printArchivedVideoSet() {

    }
}
