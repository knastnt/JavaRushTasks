package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

public class LoginCommand implements Command {
    private long num = 123456789012L;
    private int pin = 1234;
    @Override
    public void execute() throws InterruptOperationException {
        while (true){
            long unum;
            int upin;
            try {
                ConsoleHelper.writeMessage("Введите номер карты");
                unum = Long.parseLong(ConsoleHelper.readString());
                if (num < 100000000000L || num > 999999999999L) throw new Exception("Не верный номер кары.");
                ConsoleHelper.writeMessage("Введите пин-код карты");
                upin = Integer.parseInt(ConsoleHelper.readString());
                if (pin < 1000 || pin > 9999) throw new Exception("Не верный пин-код.");

                if (pin != upin || num != unum)  throw new Exception("Не верные данные.");
                ConsoleHelper.writeMessage("Верификация прошла успешно.");
                break;
            }catch (InterruptOperationException e){
                throw e;
            }catch (Exception e){
                ConsoleHelper.writeMessage("Не верные данные.");
            }
        }
    }
}
