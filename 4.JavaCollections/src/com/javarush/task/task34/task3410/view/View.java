package com.javarush.task.task34.task3410.view;

import javax.swing.*;
import com.javarush.task.task34.task3410.controller.Controller;

import java.awt.*;

public class View extends JFrame {
    private Controller controller;

    public View(Controller controller) throws HeadlessException {
        this.controller = controller;
    }
}
