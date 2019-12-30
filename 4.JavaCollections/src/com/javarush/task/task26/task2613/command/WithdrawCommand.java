package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;


class WithdrawCommand implements Command {
    private ResourceBundle res = PropertyResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        ConsoleHelper.writeMessage(res.getString("specify.amount"));
//        ConsoleHelper.writeMessage("Введите сумму");
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
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), sum, code));
//                ConsoleHelper.writeMessage("Успешная транзакция");
                break;
            }catch (NumberFormatException e){
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
//                ConsoleHelper.writeMessage("Введена неверная сумма. Попробуйте ещё раз");
            } catch (NotEnoughMoneyException e) {
//                ConsoleHelper.writeMessage("Не достаточно средств. Введите другую сумму");
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
            }
        }
    }
}
