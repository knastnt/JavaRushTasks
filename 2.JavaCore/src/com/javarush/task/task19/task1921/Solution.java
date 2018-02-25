package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException, ParseException {
        String fileName = args[0];
        //String fileName = "D:/1.txt";
        BufferedReader brdr = new BufferedReader(new FileReader(fileName));
        Object[] fileContentArr = brdr.lines().toArray();
        brdr.close();

        for (Object o : fileContentArr) {
            Pattern pattern = Pattern.compile("^(.*)\\s(\\d+)\\s(\\d+)\\s(\\d+)$");
            Matcher matcher = pattern.matcher((String) o);
            matcher.find();
            String x0 = matcher.group(1);
            int x1 = Integer.parseInt(matcher.group(2));
            int x2 = Integer.parseInt(matcher.group(3));
            int x3 = Integer.parseInt(matcher.group(4));

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(x1 + "/" + x2 + "/" + x3);

            PEOPLE.add(new Person(x0, date));
        }
    }
}
