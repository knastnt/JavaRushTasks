package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementStorage {
    private static AdvertisementStorage myInstance;
    private final List<Advertisement> videos = new ArrayList<>();

    private AdvertisementStorage() {
        Object someContent = new Object();
        add(new Advertisement(someContent, "First Video", 5000, 100, 3 * 60)); // 3 min
        add(new Advertisement(someContent, "Second Video", 100, 10, 15 * 60)); //15 min
        add(new Advertisement(someContent, "Third Video", 400, 2, 10 * 60)); //10 min
    }

    public static AdvertisementStorage getInstance(){
        if(myInstance == null) myInstance = new AdvertisementStorage();
        return myInstance;
    }

    public List<Advertisement> list() { // - который вернет список всех существующих доступных видео.
        return videos;
    }
    public void add(Advertisement advertisement) { // - который добавит новое видео в список videos.
        videos.add(advertisement);
    }
}
