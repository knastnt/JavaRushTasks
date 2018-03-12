package com.javarush.task.task17.task1711;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //start here - начни тут
        /*for(String s:args){
            System.out.println(s);
        }*/
        try{
            if(args.length == 0) {
                throw new Exception("Не переданы аргументы");
            }

                switch (args[0]) {
                    case "-c":
                        synchronized (allPeople) {
                            ArrayList<Integer> n = create(args);
                            for (int i : n) {
                                System.out.println(i);
                            }
                            break;
                        }
                    case "-u":
                        synchronized (allPeople) {
                            update(args);
                            break;
                        }
                    case "-d":
                        synchronized (allPeople) {
                            delete(args);
                            break;
                        }
                    case "-i":
                        synchronized (allPeople) {
                            info(args);
                            break;
                        }
                    default:
                        synchronized (allPeople) {
                            throw new Exception("Некорректный параметр");
                        }
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static synchronized ArrayList<Integer> create (String[] args) throws Exception{
        ArrayList<Integer> toReturn = new ArrayList<Integer>();
        if((args.length-1)%3 != 0){
            throw new Exception("Некорректные параметры операции");
        }
        for(int i = 0; i < args.length-1; i+=3) {
            //System.out.println(i);

            try {
                if (args[i + 2].equals("м")) {
                    allPeople.add(Person.createMale(args[i + 1], new SimpleDateFormat("dd/MM/yyyy").parse(args[i+3])));
                } else if (args[i + 2].equals("ж")) {
                    allPeople.add(Person.createFemale(args[i + 1], new SimpleDateFormat("dd/MM/yyyy").parse(args[i+3])));
                } else {
                    throw new Exception("Пол должен быть м/ж");
                }
            }catch (IllegalArgumentException e){
                throw new Exception("Неверный формат параметров");
            }
            toReturn.add(allPeople.size() - 1);

        }

        return toReturn;
    }

    private static synchronized void update (String[] args) throws Exception{
        if((args.length-1)%4 != 0){
            throw new Exception("Некорректные параметры операции");
        }
        for(int i = 0; i < args.length-1; i+=4) {
            //System.out.println(i);

            try {
                Person person = allPeople.get(Integer.parseInt(args[i + 1]));

                person.setName(args[i + 2]);

                if (args[i + 3].equals("м")) {
                    person.setSex(Sex.MALE);
                } else if (args[i + 3].equals("ж")) {
                    person.setSex(Sex.FEMALE);
                } else {
                    throw new Exception("Пол должен быть м/ж");
                }

                person.setBirthDay(new SimpleDateFormat("dd/MM/yyyy").parse(args[i+4]));

            }catch (IllegalArgumentException e){
                throw new Exception("Неверный формат параметров");
            }
        }
    }

    private static synchronized void delete (String[] args) throws Exception{
        if(args.length < 2){
            throw new Exception("Некорректные параметры операции");
        }
        for(int i = 1; i < args.length; i++) {
            //System.out.println(i);
            try {
                Person person = allPeople.get(Integer.parseInt(args[i]));

                person.setName(null);
                person.setSex(null);
                person.setBirthDay(null);

            }catch (Exception e){
                throw new Exception("Неверный формат параметров");
            }
        }
    }

    private static synchronized void info (String[] args) throws Exception{
        if(args.length < 2){
            throw new Exception("Некорректные параметры операции");
        }
        for(int i = 1; i < args.length; i++) {
            //System.out.println(i);
            try {
                Person person = allPeople.get(Integer.parseInt(args[i]));
                String sexString;

                if (person.getSex() == Sex.MALE) {
                    sexString="м";
                } else{
                    sexString="ж";
                }

                System.out.println(person.getName() + " " + sexString + " " + new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(person.getBirthDay()));

            }catch (Exception e){
                throw new Exception("Неверный формат параметров");
            }
        }
    }
}
