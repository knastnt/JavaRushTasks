package com.javarush.task.task14.task1408;

public class UkrainianHen extends Hen implements Country {
    private int CountOfEggsPerMonth = 1;
    private String country = UKRAINE;

    @Override
    public int getCountOfEggsPerMonth() {
        return CountOfEggsPerMonth;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " Моя страна - " + country + ". Я несу " + CountOfEggsPerMonth + " яиц в месяц.";
    }
}
