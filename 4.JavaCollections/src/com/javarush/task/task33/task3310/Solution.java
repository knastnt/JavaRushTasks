package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.OurHashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
//        testStrategy(new HashMapStorageStrategy(), 10000);
        testStrategy(new OurHashMapStorageStrategy(), 10000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> toReturn = new HashSet<>();
        for (String string : strings) {
            toReturn.add(shortener.getId(string));
        }
        return toReturn;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> toReturn = new HashSet<>();
        for (Long key : keys) {
            toReturn.add(shortener.getString(key));
        }
        return toReturn;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        System.out.println(strategy.getClass().getSimpleName());
        Set<String> set = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            set.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);
        Date t1 = new Date();
        Set<Long> result = getIds(shortener, set);
        Date t2 = new Date();
        Date t3 = new Date();
        Set<String> result2 = getStrings(shortener, result);
        Date t4 = new Date();
        System.out.println(t2.getTime()-t1.getTime());
        System.out.println(t4.getTime()-t3.getTime());
        if(set.equals(result2)){
            System.out.println("Тест пройден.");
        }else{
            System.out.println("Тест не пройден.");
        }
    }
}
