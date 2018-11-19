package com.javarush.task.task21.task2103;

/* 
Все гениальное - просто!
*/
public class Solution {
    public static boolean calculateOld(boolean a, boolean b, boolean c, boolean d) {
        return (a && b && c && !d) || (!a && c) || (!b && c) || (c && d);
    }
    public static boolean calculate(boolean a, boolean b, boolean c, boolean d) {
        //return (a && b && c) || (!a && c) || (!b && c) || (c );
        //return c && ((a && b) || !a || !b);
        return c;
    }

    public static void main(String[] args) {
        boolean a;
        boolean b;
        boolean c;
        boolean d;

        boolean result = true;

        for(int i=1; i<16; i++){
            System.out.println(String.format("%04d", Integer.parseInt(Integer.toBinaryString(i))));

            a = ((i & 1) == 1 ? true : false);
            b = ((i & 2) == 2 ? true : false);
            c = ((i & 4) == 4 ? true : false);
            d = ((i & 8) == 8 ? true : false);

            System.out.println(calculateOld(a,b,c,d));
            System.out.println(calculate(a,b,c,d));
            System.out.println("---------");
            if(calculate(a,b,c,d) != calculateOld(a,b,c,d)) result=false;
        }
        System.out.println(result);
    }
}
