package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.statistic.event.EventDataRow;

public class StatisticManager {
    private static StatisticManager statisticManager;

    private StatisticManager() {
    }

    public static StatisticManager getInstance(){
        if (statisticManager == null) { statisticManager = new StatisticManager(); }
        return statisticManager;
    }

    public void register(EventDataRow data){

    }
}
