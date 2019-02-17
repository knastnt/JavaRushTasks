package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;

import java.util.ArrayList;
import java.util.Comparator;
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

        ArrayListOfAdvertisements recursToShow = new ArrayListOfAdvertisements();

        ArrayListOfAdvertisements selectedToShow = new ArrayListOfAdvertisements();
        func (storage.list(), recursToShow, 0, selectedToShow);

        if (selectedToShow.size()<1) throw new NoVideoAvailableException();

        for (Advertisement adv0 : selectedToShow) {
            System.out.println(adv0.getName() + " is displaying... " + adv0.getAmountPerOneDisplaying() + ", " + adv0.getAmountPerOneDisplaying()*1000/adv0.getDuration());
            adv0.revalidate();
        }

    }

    public void func (List<Advertisement> storageList, ArrayListOfAdvertisements recursToShow, int deep, ArrayListOfAdvertisements selectedToShow) {

        for (int i = deep; i < storageList.size(); i++) {
            Advertisement adv = storageList.get(i);

            if (recursToShow.contains(adv)) { continue; }

            if (adv.getHits() < 1) { continue; }

            if (adv.getDuration() <= timeSeconds - getBusySec(recursToShow)) {

                if(!recursToShow.contains(adv)) {
                    recursToShow.add(adv);
                }

                func(storageList, recursToShow, deep + 1, null);
            }

            if (deep == 0) {



                recursToShow.sort(new Comparator<Advertisement>() {
                    @Override
                    public int compare(Advertisement o1, Advertisement o2) {
                        int result = (int) (o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying());
                        if(result == 0) { result = (int) (o1.getAmountPerOneDisplaying()*1000/o1.getDuration() - o2.getAmountPerOneDisplaying()*1000/o2.getDuration()); }
                        return result;
                    }
                });

                if(!selectedToShow.equals(recursToShow)) {
                    // Если данный набор отличен от выбранного
                    if (selectedToShow.getAllAmount() < recursToShow.getAllAmount()) {
                        // Если стоит дороже
                        selectedToShow.clear();
                        selectedToShow.addAll(recursToShow);
                    }else {
                        if (selectedToShow.getAllAmount() == recursToShow.getAllAmount()) {
                            // Если стоит также
                            if (selectedToShow.getAllDuration() < recursToShow.getAllDuration()) {
                                //Если длиньше
                                selectedToShow.clear();
                                selectedToShow.addAll(recursToShow);
                            }else {
                                if (selectedToShow.getAllDuration() == recursToShow.getAllDuration()) {
                                    // Еслитакой же длины
                                    if (selectedToShow.size() > recursToShow.size()) {
                                        //меньше роликов
                                        selectedToShow.clear();
                                        selectedToShow.addAll(recursToShow);
                                    }
                                }
                            }
                        }
                    }


                }
                recursToShow = new ArrayListOfAdvertisements();
            }

        }
    }

    private int getBusySec(ArrayListOfAdvertisements recursToShow){
        int toReturn = 0;

        for (Advertisement adv : recursToShow){
            toReturn += adv.getDuration();
        }

        return toReturn;
    }

    private static class ArrayListOfAdvertisements extends ArrayList<Advertisement> {
        public int getAllDuration(){
            int toReturn = 0;
            for (Advertisement advertisement : this) {
                toReturn += advertisement.getDuration();
            }
            return toReturn;
        }

        public long getAllAmount(){
            long toReturn = 0;
            for (Advertisement advertisement : this) {
                toReturn += advertisement.getAmountPerOneDisplaying();
            }
            return toReturn;
        }
    }
}