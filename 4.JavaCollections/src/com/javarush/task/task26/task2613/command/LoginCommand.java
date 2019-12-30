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
//    private ResourceBundle validCreditCards = PropertyResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
//    private ResourceBundle res = PropertyResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");
//
//    @Override
//    public void execute() throws InterruptOperationException {
//        ConsoleHelper.writeMessage(res.getString("before"));
//        ConsoleHelper.writeMessage(res.getString("specify.data"));
//        long num;
//        int pin=0;
//        while (true){
//            try {
////                ConsoleHelper.writeMessage("Введите номер карты");
//                num = Long.parseLong(ConsoleHelper.readString());
////                ConsoleHelper.writeMessage("Введите пин-код карты");
//                pin = Integer.parseInt(ConsoleHelper.readString());
//
//                if (num < 100000000000L || num > 999999999999L) throw new Exception("details");
//                if (pin < 1000 || pin > 9999) throw new Exception("details");
//
//                if (validCreditCards != null && validCreditCards.containsKey(String.valueOf(num))){
//                    if (!validCreditCards.getString(String.valueOf(num)).equals(String.valueOf(pin))) throw new Exception();
////                    ConsoleHelper.writeMessage("Верификация прошла успешно.");
//                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), String.valueOf(num)));
//                    break;
//                }
//
//                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), String.valueOf(pin)));
//                throw new Exception();
//            }catch (InterruptOperationException e){
//                throw e;
//            }catch (Exception e){
////                ConsoleHelper.writeMessage("Не верные данные.");
//                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), String.valueOf(pin)));
//                if (e.getMessage().equals("details")){
//                    ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
//                }else {
//                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
//                }
//
//            }
//        }
//    }

    private ResourceBundle validCreditCards = PropertyResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = PropertyResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("specify.data"));

        String cardNumber = ConsoleHelper.readString();
        String pin = ConsoleHelper.readString();

        while (true) {
            if (isInvalidCardNumber(cardNumber) || isInvalidPinCode(pin)) {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));

                cardNumber = ConsoleHelper.readString();
                pin = ConsoleHelper.readString();

            } else {
                if ( ! this.validCreditCards.containsKey(cardNumber) || ! pin.equals(this.validCreditCards.getString(cardNumber))) {
                    ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), cardNumber));
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                    cardNumber = ConsoleHelper.readString();
                    pin = ConsoleHelper.readString();
                    continue;
                }

                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), cardNumber));;
                break;
            }
        }
    }

    private boolean isInvalidCardNumber(String cardNumber) {
        return ! cardNumber.matches("\\d{12}");
    }

    private boolean isInvalidPinCode(String pin) {
        return ! pin.matches("\\d{4}");
    }
}
