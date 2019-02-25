package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.*;

public class DirectorTablet {
    public void printAdvertisementProfit() {
        Double total = 0.0;
        NavigableMap<Date, Double> profits = StatisticManager.getInstance().getAdvertisementProfit();

        for (Map.Entry<Date, Double> e : profits.entrySet()){
            ConsoleHelper.writeMessage( new SimpleDateFormat("dd-MMM-yyyy").format(e.getKey()) + " - " + String.format("%.2f", e.getValue()) );
            total += e.getValue();
        }

        ConsoleHelper.writeMessage("Total - " + String.format("%.2f", total));
    }

    public void printCookWorkloading() {
        NavigableMap<Date, TreeMap<String, Integer>> workloading = StatisticManager.getInstance().getCookWorkloading();

        for (Map.Entry<Date, TreeMap<String, Integer>> e : workloading.entrySet()){
            ConsoleHelper.writeMessage( new SimpleDateFormat("dd-MMM-yyyy").format(e.getKey()) );
            for (Map.Entry<String, Integer> e2 : e.getValue().entrySet()){
                ConsoleHelper.writeMessage( e2.getKey() + " - " + (int)Math.ceil(e2.getValue()) + " min" );
            }
        }

        ConsoleHelper.writeMessage( "" );
    }

    public void printActiveVideoSet() {
        List<Advertisement> list = StatisticAdvertisementManager.getInstance().getAdvertisementsWithActivity(true);
        list.sort(new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
            }
        });
        for (Advertisement advertisement : list) {
            ConsoleHelper.writeMessage(advertisement.getName() + " - " + advertisement.getHits());
        }
    }

    public void printArchivedVideoSet() {
        List<Advertisement> list = StatisticAdvertisementManager.getInstance().getAdvertisementsWithActivity(false);
        list.sort(new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
            }
        });
        for (Advertisement advertisement : list) {
            ConsoleHelper.writeMessage(advertisement.getName());
        }
    }
}
