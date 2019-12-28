package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

class ExitCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Действительно ли Вы хотите выйти? <y,n>");
        if (ConsoleHelper.readString().toLowerCase().equals("y")){
            ConsoleHelper.writeMessage("До свидания!");
        }
    }
}
