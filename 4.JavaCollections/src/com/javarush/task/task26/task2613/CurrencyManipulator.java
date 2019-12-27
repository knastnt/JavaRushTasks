package com.javarush.task.task26.task2613;

import java.util.HashMap;
import java.util.Map;

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
}
