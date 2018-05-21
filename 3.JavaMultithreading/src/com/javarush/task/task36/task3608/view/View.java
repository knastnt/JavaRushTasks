package com.javarush.task.task36.task3608.view;

import com.javarush.task.task36.task3608.controller.Controller;

public interface View {
    void refresh();

    void setController(Controller controller);
}
