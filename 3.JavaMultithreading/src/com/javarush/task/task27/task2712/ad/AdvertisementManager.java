package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

/*public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;


    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public ArrayListOfAdvertisements processVideos() throws NoVideoAvailableException {

        if ( storage.list().size() < 1 ) throw new NoVideoAvailableException();

        ArrayListOfAdvertisements recursToShow = new ArrayListOfAdvertisements();

        ArrayListOfAdvertisements selectedToShow = new ArrayListOfAdvertisements();
        func (storage.list(), recursToShow, 0, selectedToShow);

        if (selectedToShow.size()<1) throw new NoVideoAvailableException();

        for (Advertisement adv0 : selectedToShow) {
            //System.out.println(adv0.getName() + " is displaying... " + adv0.getAmountPerOneDisplaying() + ", " + adv0.getAmountPerOneDisplaying()*1000/adv0.getDuration());
            adv0.revalidate();
        }

        return selectedToShow;
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

    public static class ArrayListOfAdvertisements extends ArrayList<Advertisement> {
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
*/

    /**
     * Created by Sukora Stas.
     */
    public class AdvertisementManager {
        private final AdvertisementStorage storage=AdvertisementStorage.getInstance();
        private int timeSeconds;

        public AdvertisementManager(int timeSeconds) {
            this.timeSeconds = timeSeconds;
        }

        public void processVideos() throws NoVideoAvailableException
        {
            List<Advertisement> videoList=new ArrayList<>(storage.list());
            if (videoList.size()==0)
                throw new NoVideoAvailableException();

            Collections.sort(videoList, new Comparator<Advertisement>() {
                @Override
                public int compare(Advertisement o1, Advertisement o2) {
                    int result = Long.compare(o1.getAmountPerOneDisplaying(), o2.getAmountPerOneDisplaying());
                    if (result != 0)
                        return -result;

                    long oneSecondCost1 = o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration();
                    long oneSecondCost2 = o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration();

                    return Long.compare(oneSecondCost1, oneSecondCost2);
                }
            });

            long sumAmount=0;
            int sumDuration=0;

            List<Advertisement> list=new ArrayList<>();

            int timeLeft = timeSeconds;
            for (Advertisement advertisement : videoList) {
                if (timeLeft < advertisement.getDuration() || advertisement.getHits()<=0) {
                    continue;
                }
                list.add(advertisement);
                sumAmount+=advertisement.getAmountPerOneDisplaying();
                sumDuration+=advertisement.getDuration();
                timeLeft -= advertisement.getDuration();
            }

            if (timeLeft == timeSeconds) {
                throw new NoVideoAvailableException();
            }

            StatisticManager.getInstance().register(new VideoSelectedEventDataRow(list, sumAmount, sumDuration));

            for (Advertisement advertisement : list) {
                ConsoleHelper.writeMessage(advertisement.getName() + " is displaying... "
                        + advertisement.getAmountPerOneDisplaying() + ", "
                        + advertisement.getAmountPerOneDisplaying() * 1000 / advertisement.getDuration());
                advertisement.revalidate();
            }
        }
    }