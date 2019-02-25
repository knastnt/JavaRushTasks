package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;

public class DirectorTablet {
    public void printAdvertisementProfit() {
        Double total = 0.0;
        SortedMap<Date, Double> profits = StatisticManager.getInstance().getTotalProfit();

        for (Map.Entry<Date, Double> e : profits.entrySet()){
            ConsoleHelper.writeMessage( new SimpleDateFormat("dd-MMM-yyyy").format(e.getKey()) + " - " + e.getValue().toString() );
            total += e.getValue();
        }

        ConsoleHelper.writeMessage("Total - " + total.toString());
    }

    public void printCookWorkloading() {

    }

    public void printActiveVideoSet() {

    }

    public void printArchivedVideoSet() {

    }
}
