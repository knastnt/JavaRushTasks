package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.javarush.task.task27.task2712.statistic.event.EventType.SELECTED_VIDEOS;

public class StatisticManager {
    private static StatisticManager statisticManager;
    private StatisticStorage statisticStorage = new StatisticStorage();
    private Set cooks = new HashSet<Cook>();

    private StatisticManager() {
    }

    public static StatisticManager getInstance(){
        if (statisticManager == null) { statisticManager = new StatisticManager(); }
        return statisticManager;
    }

    public void register(EventDataRow data){
        statisticStorage.put(data);
    }

    public void register(Cook cook) {
        cooks.add(cook);
    }

    public SortedMap<Date, Double> getTotalProfit() {
        SortedMap<Date, Double> toReturn = new TreeMap<>();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        for (EventDataRow eventDataRow : statisticStorage.get(SELECTED_VIDEOS)) {

            Date todayWithZeroTime = null;
            try {
                todayWithZeroTime = formatter.parse(formatter.format(eventDataRow.getDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            double profit = 0;

            if(toReturn.containsKey(todayWithZeroTime)){
                profit += toReturn.get(todayWithZeroTime);
            }

            profit += ((VideoSelectedEventDataRow)eventDataRow).getAmount();

            toReturn.put(todayWithZeroTime, profit);
            //toReturn.put(eventDataRow.getDate(), profit);
        }
        return toReturn;
    }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public StatisticStorage() {
            for (EventType eventType : EventType.values()) {
                storage.put(eventType, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data){
            storage.get(data.getType()).add(data);
        }

        public List<EventDataRow> get(EventType eventType) {
            return storage.get(eventType);
        }
    }
}
