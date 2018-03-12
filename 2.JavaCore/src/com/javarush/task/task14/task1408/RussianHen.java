package com.javarush.task.task14.task1408;

public class RussianHen extends Hen implements Country {
    private static int CountOfEggsPerMonth = 2;
    private static String country = RUSSIA;

    @Override
    public int getCountOfEggsPerMonth() {
        return CountOfEggsPerMonth;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " Моя страна - " + country + ". Я несу " + CountOfEggsPerMonth + " яиц в месяц.";
    }
}
