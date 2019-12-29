package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class CurrencyManipulator {
    private String currencyCode; // - код валюты, например, USD. Состоит из трех букв.
    private Map<Integer, Integer> denominations = new HashMap<>(); // - это Map<номинал, количество>.

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count){
        if(denominations.containsKey(denomination)){
            denominations.put(denomination, count + denominations.get(denomination));
        }else{
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount(){
        int amount = 0;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            amount += entry.getKey() * entry.getValue();
        }
        return amount;
    }

    public boolean hasMoney(){
        return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int expectedAmount){
        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> r = new HashMap<>();
        AtomicInteger rSum = new AtomicInteger(0);
//        r.put(500, 2);
//        r.put(100, 3);
//        r.put(1000, 1);

        try {
            denominations.entrySet().stream().sorted((e1, e2) -> e2.getKey() - e1.getKey()).map(entry -> {
                while (expectedAmount - rSum.get() - entry.getKey() >= 0 && entry.getValue() > 0) {
                    if (r.containsKey(entry.getKey())) {
                        r.put(entry.getKey(), 1 + r.get(entry.getKey()));
                    } else {
                        r.put(entry.getKey(), 1);
                    }
                    rSum.addAndGet(entry.getKey());
                    entry.setValue(entry.getValue() - 1);
                }
                return entry;
            }).count();

            if (rSum.get() != expectedAmount) throw new NotEnoughMoneyException();

        }catch (ConcurrentModificationException | NotEnoughMoneyException e){
            //Возврат денег
            for (Map.Entry<Integer, Integer> entry : r.entrySet()) {
                denominations.put(entry.getKey(), denominations.get(entry.getKey()) + entry.getValue());
            }
            throw new NotEnoughMoneyException();
        }


        return r;
    }
}
