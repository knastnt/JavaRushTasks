package com.javarush.task.task22.task2210rollbox;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static class zakaz{
        Integer count;
        Date first;
        Date last;

        public zakaz(Integer count, Date first, Date last) {
            this.count = count;
            this.first = first;
            this.last = last;
        }


    }

    public static void main(String[] args) throws IOException, ParseException {
        //Скармливаем файл содержания:
        /*
        89243071504	16.12.2017, 19:27, Суббота
        80000000000	14.10.2017, 15:14, Суббота
        80000000000	10.10.2017, 17:33, Вторник
        80000000000	17.09.2017, 17:04, Воскресенье
        80000000000	15.09.2017, 15:01, Пятница
        80000000000	06.09.2017, 18:55, Среда
        80000000000	11.08.2017, 19:51, Пятница
        80000000000	10.08.2017, 20:31, Четверг
        */

        HashMap<Long, zakaz> map = new HashMap<>();
        String fileName = "D:\\1.txt";
        int maxCount = 0;

        BufferedReader brdr = new BufferedReader(new FileReader(fileName));
        while(brdr.ready()){
            String[] readed = brdr.readLine().split(" |\t");

            long readedNumber = Long.parseLong(readed[0]);
            Date readedDate = new SimpleDateFormat("dd.MM.yyyy,").parse(readed[1]);

            if(map.containsKey(readedNumber)){
                int newCount = map.get(readedNumber).count+1;
                Date currentZfirst =  map.get(readedNumber).first;
                Date currentZlast =  map.get(readedNumber).last;

                if(readedDate.before(currentZfirst)) currentZfirst = readedDate;
                if(readedDate.after(currentZlast)) currentZlast = readedDate;

                map.put(readedNumber, new zakaz(newCount, currentZfirst, currentZlast));
                if(maxCount<newCount) maxCount = newCount;
            }else{
                map.put(readedNumber, new zakaz(1, readedDate, readedDate));
            }
        }
        brdr.close();

        long[][] finalArr = new long[map.size()][2];

        FileWriter fw = new FileWriter("D:\\2.csv");

        int finalArrIndex = 0;
        for (long i = maxCount; i >= 0; i--) {
            //Проверяем, нет ли в map ключей с такими значениями
            for(Map.Entry<Long,zakaz> e : map.entrySet()){
                long val = e.getValue().count;
                if(val == i){
                    finalArr[finalArrIndex][0] = i;
                    finalArr[finalArrIndex][1] = e.getKey();
                    finalArrIndex++;
                    fw.write(e.getKey() + ";" + i + ";" + new SimpleDateFormat("dd.MM.yyyy").format(e.getValue().first) + ";" + new SimpleDateFormat("dd.MM.yyyy").format(e.getValue().last) + "\r\n");
                }
            }
        }

        fw.close();

    }
}
