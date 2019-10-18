package com.javarush.task.task35.task3502;

import java.util.ArrayList;
import java.util.List;

/*
Знакомство с дженериками
*/
public class Solution<T extends List<Solution.SomeClass>> {
    public static class SomeClass<T extends Number> {
    }

    public static void main(String[] args) {
        ArrayList<MagicWarrior> magi = new ArrayList<MagicWarrior>();
        for(int i=0;i<10;i++)
            magi.add(new MagicWarrior(50));

        ArrayList<ArcherWarrior> archers = new ArrayList<ArcherWarrior>();
        for(int i=0;i<10;i++)
            archers.add(new ArcherWarrior(60));

        boolean isMagicCooler = WarriorManager.fight(magi, archers); //ошибка компиляции!

        System.out.println(isMagicCooler);
    }
}
