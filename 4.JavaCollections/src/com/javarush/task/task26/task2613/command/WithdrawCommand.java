package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;


class WithdrawCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        ConsoleHelper.writeMessage("Введите сумму");
        while (true){
            try {
                Integer sum = Integer.parseInt(ConsoleHelper.readString());
                if (sum<=0) throw new NumberFormatException();
                if (!currencyManipulator.isAmountAvailable(sum)) throw new NotEnoughMoneyException();
                currencyManipulator
                        .withdrawAmount(sum)
                        .entrySet()
                        .stream()
                        .sorted((e1, e2) -> e2.getKey() - e1.getKey())
                        .forEach(entry -> {
                            ConsoleHelper.writeMessage("\t" + entry.getKey() + " - " + entry.getValue());
                        });
                ConsoleHelper.writeMessage("Успешная транзакция");
                break;
            }catch (NumberFormatException e){
                ConsoleHelper.writeMessage("Введена неверная сумма. Попробуйте ещё раз");
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage("Не достаточно средств. Введите другую сумму");
            }
        }
    }
}
