package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager myInstance;
    private AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    private StatisticAdvertisementManager() {
    }

    public static StatisticAdvertisementManager getInstance() {
        if (myInstance == null) myInstance = new StatisticAdvertisementManager();
        return myInstance;
    }

    public List<Advertisement> getAdvertisementsWithActivity(boolean isActive){
        List<Advertisement> toReturn = new ArrayList<>();
        for (Advertisement advertisement : advertisementStorage.list()) {
            if ( (advertisement.getHits() != 0) == isActive) {
                toReturn.add(advertisement);
            }
        }
        return toReturn;
    }
}
