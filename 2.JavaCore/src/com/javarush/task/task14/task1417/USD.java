package com.javarush.task.task14.task1417;

public class USD extends Money {
    private String abb = "USD";

    @Override
    public String getCurrencyName() {
        return abb;
    }

    public USD(double amount) {
        super(amount);
    }
}
