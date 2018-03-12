package com.javarush.task.task14.task1417;

public class Hrivna extends Money {
    private String abb = "HRN";

    @Override
    public String getCurrencyName() {
        return abb;
    }

    public Hrivna(double amount) {
        super(amount);
    }
}
