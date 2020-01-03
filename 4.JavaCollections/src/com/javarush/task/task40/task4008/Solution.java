package com.javarush.task.task40.task4008;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/* 
Работа с Java 8 DateTime API


  Symbol  Meaning                     Presentation      Examples
  ------  -------                     ------------      -------
   G       era                         text              AD; Anno Domini; A
   u       year                        year              2004; 04
   y       year-of-era                 year              2004; 04
   D       day-of-year                 number            189
   M/L     month-of-year               number/text       7; 07; Jul; July; J
   d       day-of-month                number            10

   Q/q     quarter-of-year             number/text       3; 03; Q3; 3rd quarter
   Y       week-based-year             year              1996; 96
   w       week-of-week-based-year     number            27
   W       week-of-month               number            4
   E       day-of-week                 text              Tue; Tuesday; T
   e/c     localized day-of-week       number/text       2; 02; Tue; Tuesday; T
   F       week-of-month               number            3

   a       am-pm-of-day                text              PM
   h       clock-hour-of-am-pm (1-12)  number            12
   K       hour-of-am-pm (0-11)        number            0
   k       clock-hour-of-am-pm (1-24)  number            0

   H       hour-of-day (0-23)          number            0
   m       minute-of-hour              number            30
   s       second-of-minute            number            55
   S       fraction-of-second          fraction          978
   A       milli-of-day                number            1234
   n       nano-of-second              number            987654321
   N       nano-of-day                 number            1234000000

   V       time-zone ID                zone-id           America/Los_Angeles; Z; -08:30
   z       time-zone name              zone-name         Pacific Standard Time; PST
   O       localized zone-offset       offset-O          GMT+8; GMT+08:00; UTC-08:00;
   X       zone-offset 'Z' for zero    offset-X          Z; -08; -0830; -08:30; -083015; -08:30:15;
   x       zone-offset                 offset-x          +0000; -08; -0830; -08:30; -083015; -08:30:15;
   Z       zone-offset                 offset-Z          +0000; -0800; -08:00;

   p       pad next                    pad modifier      1

   '       escape for text             delimiter
   ''      single quote                literal           '
   [       optional section start
   ]       optional section end
   #       reserved for future use
   {       reserved for future use
   }       reserved for future use


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
        LocalDateTime localDateTime = null;
        LocalDate localDate = null;
        LocalTime localTime = null;
        DateTimeFormatter dTF;


        try {
            dTF = DateTimeFormatter.ofPattern("d.M.y H:m:s");
            localDateTime = LocalDateTime.parse(date, dTF);
        } catch (DateTimeParseException e) {}

        try {
            dTF = DateTimeFormatter.ofPattern("d.M.y");
            localDate = LocalDate.parse(date, dTF);
        } catch (DateTimeParseException e) {}

        try {
            dTF = DateTimeFormatter.ofPattern("H:m:s");
            localTime = LocalTime.parse(date, dTF);
        } catch (DateTimeParseException e) {}


        if(localDateTime!=null){
                System.out.println("День: " + localDateTime.getDayOfMonth());
                System.out.println("День недели: " + localDateTime.getDayOfWeek().getValue());
                System.out.println("День месяца: " + localDateTime.getDayOfMonth());
                System.out.println("День года: " + localDateTime.getDayOfYear());
                System.out.println("Неделя месяца: " + DateTimeFormatter.ofPattern("W").format(localDateTime));
                System.out.println("Неделя года: " + DateTimeFormatter.ofPattern("w").format(localDateTime));
                System.out.println("Месяц: " + localDateTime.getMonth().getValue());
                System.out.println("Год: " + localDateTime.getYear());

                System.out.println("AM или PM: " + DateTimeFormatter.ofPattern("a").format(localDateTime));
                System.out.println("Часы: " + DateTimeFormatter.ofPattern("K").format(localDateTime));
                System.out.println("Часы дня: " + localDateTime.getHour());
                System.out.println("Минуты: " + localDateTime.getMinute());
                System.out.println("Секунды: " + localDateTime.getSecond());
        }
        if(localDate!=null){
                System.out.println("День: " + localDate.getDayOfMonth());
                System.out.println("День недели: " + localDate.getDayOfWeek().getValue());
                System.out.println("День месяца: " + localDate.getDayOfMonth());
                System.out.println("День года: " + localDate.getDayOfYear());
                System.out.println("Неделя месяца: " + DateTimeFormatter.ofPattern("W").format(localDate));
                System.out.println("Неделя года: " + DateTimeFormatter.ofPattern("w").format(localDate));
                System.out.println("Месяц: " + localDate.getMonth().getValue());
                System.out.println("Год: " + localDate.getYear());
        }
        if(localTime!=null){
            System.out.println("AM или PM: " + DateTimeFormatter.ofPattern("a").format(localTime));
            System.out.println("Часы: " + DateTimeFormatter.ofPattern("K").format(localTime));
            System.out.println("Часы дня: " + localTime.getHour());
            System.out.println("Минуты: " + localTime.getMinute());
            System.out.println("Секунды: " + localTime.getSecond());
        }
    }
}
