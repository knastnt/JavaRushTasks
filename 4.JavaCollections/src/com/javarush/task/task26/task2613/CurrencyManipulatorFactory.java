package com.javarush.task.task26.task2613;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> map = new HashMap<>();

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode){
        if (!map.containsKey(currencyCode.toLowerCase())){
            map.put(currencyCode.toLowerCase(), new CurrencyManipulator(currencyCode));
        }
        return map.get(currencyCode.toLowerCase());
    }

    private CurrencyManipulatorFactory() {
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators(){
        return map.values();
    }
}
