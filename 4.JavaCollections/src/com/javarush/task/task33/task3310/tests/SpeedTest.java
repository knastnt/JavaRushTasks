package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {
    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids){
        Date t1 = new Date();
        for (String string : strings) {
            ids.add(shortener.getId(string));
        }
        Date t2 = new Date();
        return t2.getTime()-t1.getTime();
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings){
        Date t3 = new Date();
        for (Long key : ids) {
            strings.add(shortener.getString(key));
        }
        Date t4 = new Date();
        return t4.getTime()-t3.getTime();
    }

    @Test
    public void testHashMapStorage(){
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }

        Set<Long> set1 = new HashSet<>();
        Set<Long> set2 = new HashSet<>();

        long t1 = getTimeToGetIds(shortener1, origStrings, set1);
        long t2 = getTimeToGetIds(shortener2, origStrings, set2);

        Assert.assertTrue(t1 > t2);

        long t3 = getTimeToGetStrings(shortener1, set1, new HashSet<>());
        long t4 = getTimeToGetStrings(shortener2, set2, new HashSet<>());

        Assert.assertEquals(t3, t4, 30);
    }
}
