package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) { // - для вывода message в консоль
        System.out.println(message);
    }
    public static String readString() throws IOException { // - для чтения строки с консоли
        return br.readLine();
    }
    public static List<Dish> getAllDishesForOrder() throws IOException {  // просит пользователя выбрать блюдо и добавляет его в список.
        List<Dish> allDishesForOrder = new ArrayList<>();
        String readed;
        writeMessage("Напишите что Вы ходите заказать в нашем Меню. exit - для завершения заказа");
        writeMessage(Dish.allDishesToString());
        while(!"exit".equals(readed = readString())){
            if(Dish.contains(readed)){
                allDishesForOrder.add(Dish.valueOf(readed));
                writeMessage("блюдо добавлено, введите следующее блюдо или exit для завершения заказа");
            }else{
                writeMessage("такого блюда нет, попробуйте снова. exit - для завершения заказа");
            }
        }
        return allDishesForOrder;
    }
}
