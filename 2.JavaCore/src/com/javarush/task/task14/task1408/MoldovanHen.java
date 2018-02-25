package com.javarush.task.task14.task1408;

public class MoldovanHen extends  Hen implements Country {
    private int CountOfEggsPerMonth = 3;
    private String country = MOLDOVA;

    @Override
    public int getCountOfEggsPerMonth() {
        return CountOfEggsPerMonth;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " Моя страна - " + country + ". Я несу " + CountOfEggsPerMonth + " яиц в месяц.";
    }
}
