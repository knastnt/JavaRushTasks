package com.javarush.task.task35.task3502;

import java.util.List;

class WarriorManager
{
    public static boolean fight(Warrior w1, Warrior w2)
    {
        return w1.power > w2.power;
    }

    public static boolean fight(List<? extends Warrior> w1, List<? extends Warrior> w2)
    {
        int fst = 0;
        int scnd = 0;

        for (Warrior warrior : w1) {
            fst += warrior.power;
        }
        for (Warrior warrior : w2) {
            scnd += warrior.power;
        }
        return fst > scnd;
    }
}