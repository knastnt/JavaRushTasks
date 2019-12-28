package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.*;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        try {
            String readed = bis.readLine();
            if (readed.toLowerCase().equals("exit")) throw new InterruptOperationException();
            return readed;
        } catch (IOException e) {
            return "";
        }
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage("Введите код валюты:");
        String code;
        while ((code = readString()).length() != 3){
            writeMessage("Код валюты должен состоять из трех символов. Попробуйте ещё раз:");
        }
        return code.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {

        while (true){
            writeMessage("Введите номинал и количество банкнот через пробел");
            Pattern p = Pattern.compile("^(\\d+)\\s+(\\d+)$");
            Matcher m = p.matcher(readString());
            if (!m.find()){
                writeMessage("не верный ввод");
                continue;
            }
            int first = Integer.parseInt(m.group(1));
            int second = Integer.parseInt(m.group(2));

            if(first < 1 || second < 1){
                writeMessage("не верный ввод. Числа должны быть целые и больше нуля");
                continue;
            }
            String[] result = new String[2];
            result[0] = String.valueOf(first);
            result[1] = String.valueOf(second);
            return result;
        }
    }

    public static Operation askOperation() throws InterruptOperationException {
        while (true){
            writeMessage("Введите номер операции: 1 - INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT");
            try {
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(readString()));
            }catch (IllegalArgumentException e){
                writeMessage("Неверный ввод, повторите снова.");
            }
        }
    }
}
