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
        //if(storage.list().size()<1) throw new NoVideoAvailableException();

        HashSet<Advertisement> recursToShow = new HashSet<>();

        HashSet<HashSet<Advertisement>> selectedToShow = new HashSet<>();
        func (storage.list(), recursToShow, 0, selectedToShow);

        if (selectedToShow.size()<1) throw new NoVideoAvailableException();
    }

    public void func (List<Advertisement> storageList, HashSet<Advertisement> recursToShow, int deep, HashSet<HashSet<Advertisement>> selectedToShow) {

        for (int i = deep; i < storageList.size(); i++) {
            Advertisement adv = storageList.get(i);

            if (recursToShow.contains(adv)) { continue; }

            if (adv.getDuration() <= timeSeconds - getBusySec(recursToShow)) {

                recursToShow.add(adv);

                func(storageList, recursToShow, deep + 1, null);
            }

            if (deep == 0) {

                System.out.println("--- итерация начата ---");
                System.out.println(deep);
                int res = 0;
                for (Advertisement adv0 : recursToShow) {
                    res += adv0.getDuration();
                    System.out.println(adv0.getName() + " " + adv0.getDuration());
                }
                System.out.println(timeSeconds + " " + res);
                System.out.println("--- итерация закончена ---");

                selectedToShow.add(recursToShow);
                recursToShow = new HashSet<>();
            }

        }
    }

    private int getBusySec(HashSet<Advertisement> recursToShow){
        int toReturn = 0;

        for (Advertisement adv : recursToShow){
            toReturn += adv.getDuration();
        }

        return toReturn;
    }
}
