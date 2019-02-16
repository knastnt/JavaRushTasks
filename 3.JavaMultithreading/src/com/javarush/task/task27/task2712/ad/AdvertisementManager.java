package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;


    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException {
        if(storage.list().size()<1) throw new NoVideoAvailableException();

        HashSet<Advertisement> selectedToSwow = new HashSet<>();

        func (storage.list(), selectedToSwow, 0);
    }

    public void func (List<Advertisement> storageList, HashSet<Advertisement> selectedToSwow, int deep) {
        for (int i = 0; i < storageList.size(); i++) {
            Advertisement adv = storageList.get(i);

            if (selectedToSwow.contains(adv)) { continue; }

            if (adv.getDuration() <= timeSeconds - getBusySec(selectedToSwow)) {

                selectedToSwow.add(adv);

                func(storageList, selectedToSwow, deep + 1);
            }

            if (deep == 0) {
                System.out.println("--- итерация начата ---");
                System.out.println(deep);
                int res = 0;
                for (Advertisement adv0 : selectedToSwow) {
                    res += adv0.getDuration();
                    System.out.println(adv0.getName() + " " + adv0.getDuration());
                }
                System.out.println(timeSeconds + " " + res);
                selectedToSwow.clear();
                System.out.println("--- итерация закончена ---");
            }

        }
    }

    private int getBusySec(HashSet<Advertisement> selectedToSwow){
        int toReturn = 0;

        for (Advertisement adv : selectedToSwow){
            toReturn += adv.getDuration();
        }

        return toReturn;
    }
}
