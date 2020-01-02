package com.javarush.task.task40.task4007;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* 
Работа с датами
*/

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        //напишите тут ваш код
        SimpleDateFormat simpleDateFormat;
        Date dateParsed = null;
        Calendar calendar = Calendar.getInstance();

        boolean t=false, d=false;
        if (dateParsed == null) {
            try {
                simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                dateParsed = simpleDateFormat.parse(date);
                t=true;
                d=true;
            } catch (ParseException e) {}
        }
        if (dateParsed == null) {
            try {
                simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                dateParsed = simpleDateFormat.parse(date);
                d=true;
            } catch (ParseException e) {}
        }
        if (dateParsed == null) {
            try {
                simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                dateParsed = simpleDateFormat.parse(date);
                t=true;
            } catch (ParseException e) {}
        }
        if(dateParsed!=null){
            calendar.setTime(dateParsed);
            /*
            День: 21
            День недели: 1
            День месяца: 21
            День года: 111
            Неделя месяца: 4
            Неделя года: 17
            Месяц: 4
            Год: 2014
            AM или PM: PM
            Часы: 3
            Часы дня: 15
            Минуты: 56
            Секунды: 45
             */

            if (d) {
                System.out.println("День: " + calendar.get(Calendar.DAY_OF_MONTH));
                System.out.println("День недели: " + (calendar.get(Calendar.DAY_OF_WEEK) == 1 ? 7 : calendar.get(Calendar.DAY_OF_WEEK) - 1));
                System.out.println("День месяца: " + calendar.get(Calendar.DAY_OF_MONTH));
                System.out.println("День года: " + calendar.get(Calendar.DAY_OF_YEAR));
                System.out.println("Неделя месяца: " + calendar.get(Calendar.WEEK_OF_MONTH));
                System.out.println("Неделя года: " + calendar.get(Calendar.WEEK_OF_YEAR));
                System.out.println("Месяц: " + (calendar.get(Calendar.MONTH) + 1));
                System.out.println("Год: " + calendar.get(Calendar.YEAR));
            }
            if (t) {
                System.out.println("AM или PM: " + (calendar.get(Calendar.AM_PM) == 1 ? "PM" : "AM"));
                System.out.println("Часы: " + calendar.get(Calendar.HOUR));
                System.out.println("Часы дня: " + calendar.get(Calendar.HOUR_OF_DAY));
                System.out.println("Минуты: " + calendar.get(Calendar.MINUTE));
                System.out.println("Секунды: " + calendar.get(Calendar.SECOND));
            }
        }
    }
}
