package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = PropertyResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.verifiedCards");

    @Override
    public void execute() throws InterruptOperationException {
        while (true){
            long num;
            int pin;
            try {
                ConsoleHelper.writeMessage("Введите номер карты");
                num = Long.parseLong(ConsoleHelper.readString());
                ConsoleHelper.writeMessage("Введите пин-код карты");
                pin = Integer.parseInt(ConsoleHelper.readString());

                if (num < 100000000000L || num > 999999999999L) throw new Exception();
                if (pin < 1000 || pin > 9999) throw new Exception();

                if (validCreditCards != null && validCreditCards.containsKey(String.valueOf(num))){
                    if (!validCreditCards.getString(String.valueOf(num)).equals(String.valueOf(pin))) throw new Exception();
                    ConsoleHelper.writeMessage("Верификация прошла успешно.");
                    break;
                }

                throw new Exception();
            }catch (InterruptOperationException e){
                throw e;
            }catch (Exception e){
                ConsoleHelper.writeMessage("Не верные данные.");
            }
        }
    }
}
