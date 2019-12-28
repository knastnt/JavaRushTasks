package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

class InfoCommand implements Command {
    @Override
    public void execute() {
        for (CurrencyManipulator currencyManipulator : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            if (currencyManipulator.hasMoney()) {
                ConsoleHelper.writeMessage(currencyManipulator.getCurrencyCode() + " - " + currencyManipulator.getTotalAmount());
            }else{
                ConsoleHelper.writeMessage("No money available.");
            }
        }
        if (CurrencyManipulatorFactory.getAllCurrencyManipulators().isEmpty()){
            ConsoleHelper.writeMessage("No money available.");
        }
    }
}
