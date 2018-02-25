package com.javarush.task.task14.task1417;

public class Ruble extends Money {
    private String abb = "RUB";

    @Override
    public String getCurrencyName() {
        return abb;
    }

    public Ruble(double amount) {
        super(amount);
    }
}
