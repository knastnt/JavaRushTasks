package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private Controller controller;

    private JTabbedPane tabbedPane = new JTabbedPane(); //это будет панель с двумя вкладками.
    private JTextPane htmlTextPane = new JTextPane(); //это будет компонент для визуального редактирования html.
    private JEditorPane plainTextPane = new JEditorPane(); //это будет компонент для редактирования html в виде текста, он будет отображать код html (теги и их содержимое).


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public Controller getController() {
        return controller;
    }

    public void init(){
        initGui();
        addWindowListener(new FrameListener(this));
        setVisible(true);
    }

    public void initGui(){
        initMenuBar();
        initEditor();
        pack();
    }

    //инициализация меню
    public void initMenuBar(){

    }

    //инициализация панелей редактора
    public void initEditor(){

    }

    public void exit(){
        controller.exit();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
