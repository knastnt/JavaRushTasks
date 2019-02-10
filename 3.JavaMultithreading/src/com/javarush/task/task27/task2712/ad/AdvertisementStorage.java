package com.javarush.task.task27.task2712.ad;

public class AdvertisementStorage {
    private static AdvertisementStorage myInstance;

    private AdvertisementStorage() {
    }

    public static AdvertisementStorage getInstance(){
        if(myInstance == null) myInstance = new AdvertisementStorage();
        return myInstance;
    }
}
