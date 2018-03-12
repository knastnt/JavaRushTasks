package com.javarush.task.task19.task1904;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) {
    }

    public static class PersonScannerAdapter implements PersonScanner {
        private final Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException, ParseException {
            String[] rd = fileScanner.nextLine().split(" ");
            //Date birthDate = new Date(Integer.parseInt(rd[5]), Integer.parseInt(rd[4]), Integer.parseInt(rd[3]));

            SimpleDateFormat sdf  = new SimpleDateFormat("dd MM yyyy");
            Date birthDate = sdf.parse(String.format( "%s %s %s", Integer.parseInt(rd[3]), Integer.parseInt(rd[4]), Integer.parseInt(rd[5])));

            return new Person(rd[1], rd[2], rd[0], birthDate);
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
