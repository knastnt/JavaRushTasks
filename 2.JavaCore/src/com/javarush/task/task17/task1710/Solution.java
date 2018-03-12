package com.javarush.task.task17.task1710;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //start here - начни тут
        /*for(String s:args){
            System.out.println(s);
        }*/
        if(args.length == 0) return;
        switch (args[0]){
            case "-c":
                System.out.println(create(args));
                break;
            case "-u":
                update(args);
                break;
            case "-d":
                delete(args);
                break;
            case "-i":
                info(args);
                break;
        }
    }

    private static int create(String[] args){
        try {
            int index = allPeople.size();
            if ("м".equals(args[2])) {
                allPeople.add(Person.createMale(args[1], new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH).parse(args[3])));  //сегодня родился    id=0
            } else if ("ж".equals(args[2])) {
                allPeople.add(Person.createFemale(args[1], new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH).parse(args[3])));  //сегодня родился    id=0
            } else {
                throw new Exception();
            }
            return index;
        }
        catch (Exception e){
            System.out.println("Ошибка добавления записи");
            return -1;
        }
    }

    private static void update(String[] args){
        try {
            Person person = allPeople.get(Integer.parseInt(args[1]));
            person.setName(args[2]);
            if("м".equals(args[3])){
                person.setSex(Sex.MALE);
            }else if("ж".equals(args[3])){
                person.setSex(Sex.FEMALE);
            }else{
                throw new Exception();
            }
            person.setBirthDay(new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH).parse(args[4]));
        }
        catch (Exception e){
            System.out.println("Ошибка обновления записи");
        }
    }

    private static void delete(String[] args){
        try {
            Person person = allPeople.get(Integer.parseInt(args[1]));
            person.setName(null);
            person.setSex(null);
            person.setBirthDay(null);
        }
        catch (Exception e){
            System.out.println("Ошибка удаления записи");
        }
    }

    private static void info(String[] args){
        try {
            Person person = allPeople.get(Integer.parseInt(args[1]));
            String sex="";
            if(person.getSex()==Sex.MALE){
                sex="м";
            }
            if(person.getSex()==Sex.FEMALE){
                sex="ж";
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

            System.out.println(person.getName() + " " + sex + " " + sdf.format(person.getBirthDay()));
        }
        catch (Exception e){
            System.out.println("Ошибка отображения записи");
        }
    }
}
