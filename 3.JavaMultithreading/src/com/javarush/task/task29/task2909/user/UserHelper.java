package com.javarush.task.task29.task2909.user;

import java.util.concurrent.atomic.AtomicInteger;

public class UserHelper {
    private User userAnya = new User("Аня", "Смирнова", 10);
    private User userRoma = new User("Рома", "Виноградов", 30);

    private boolean isManAnya = false;
    private boolean isManRoma = true;

    public void printUsers() {
        /*System.out.println("Имя: " + userAnya.getName());
        System.out.println("Фамилия: " + userAnya.getSurname());*/
        userAnya.printInfo();
        //printAdditionalInfo(userAnya);
        userAnya.printAdditionalInfo();

        /*System.out.println("Имя: " + userRoma.getName());
        System.out.println("Фамилия: " + userRoma.getSurname());*/
        userRoma.printInfo();
        //printAdditionalInfo(userRoma);
        userRoma.printAdditionalInfo();
    }



    public int calculateAverageAge() {
        User userUra = new User("Юра", "Карп", 28);

        return (userAnya.getAge() + userRoma.getAge() + userUra.getAge()) / 3;
    }

    public int calculateRate(AtomicInteger base, int age, boolean hasWork, boolean hasHouse) {
        int toReturn = base.get();
        toReturn=(toReturn + age / 100);
        toReturn = ((int) (toReturn * (hasWork ? 1.1 : 0.9)));
        toReturn = ((int) (toReturn * (hasHouse ? 1.1 : 0.9)));
        return toReturn;
    }

    public String getBossName(User user) {
        Work work = user.getWork();
        return work.getBoss();
    }
}