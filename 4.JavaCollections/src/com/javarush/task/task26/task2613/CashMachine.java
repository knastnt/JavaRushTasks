package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.command.CommandExecutor;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class CashMachine {
    public static final String RESOURCE_PATH = CashMachine.class.getPackage().getName() + ".resources.";

    public static void main(String[] args) throws NotEnoughMoneyException {
//        CurrencyManipulator h = CurrencyManipulatorFactory.getManipulatorByCurrencyCode("zzz");
//        h.addAmount(500, 2);
//        h.addAmount(200, 2);
//        h.addAmount(100, 1);
//        h.addAmount(50, 12);
//        h.withdrawAmount(600);
//        if (true) return;

        try {
            Locale.setDefault(Locale.ENGLISH);
            CommandExecutor.execute(Operation.LOGIN);
            Operation operation;
            do {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while (operation != Operation.EXIT);
        }catch (InterruptOperationException e){

            ConsoleHelper.printExitMessage();
//            ConsoleHelper.writeMessage("До свидания!");
        }
    }
}
