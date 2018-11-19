package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import javafx.scene.layout.BorderPane;

import javax.swing.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private Controller controller;

    private JTabbedPane tabbedPane = new JTabbedPane(); //это будет панель с двумя вкладками.
    private JTextPane htmlTextPane = new JTextPane(); //это будет компонент для визуального редактирования html.
    private JEditorPane plainTextPane = new JEditorPane(); //это будет компонент для редактирования html в виде текста, он будет отображать код html (теги и их содержимое).

    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("view как слушатель - услышал");
        switch (e.getActionCommand()){
            case "О программе":
                showAbout();
                break;
        }
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
        JMenuBar jMenuBar = new JMenuBar();
        //Файл, Редактировать, Стиль, Выравнивание, Цвет, Шрифт и Помощь.
        MenuHelper.initFileMenu(this,jMenuBar);
        MenuHelper.initEditMenu(this,jMenuBar);
        MenuHelper.initStyleMenu(this,jMenuBar);
        MenuHelper.initAlignMenu(this,jMenuBar);
        MenuHelper.initColorMenu(this,jMenuBar);
        MenuHelper.initFontMenu(this,jMenuBar);
        MenuHelper.initHelpMenu(this,jMenuBar);

        getContentPane().add(jMenuBar, BorderLayout.NORTH);
    }

    //инициализация панелей редактора
    public void initEditor(){
        htmlTextPane.setContentType("text/html");

        JScrollPane jScrollPane = new JScrollPane(htmlTextPane);
        tabbedPane.add("HTML",jScrollPane);
        JScrollPane jScrollPane1 = new JScrollPane(plainTextPane);
        tabbedPane.add("Текст",jScrollPane1);

        tabbedPane.setPreferredSize(new Dimension(600,400));
        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));

        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    public void exit(){
        controller.exit();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void selectedTabChanged(){

    }

    public boolean canUndo(){
        return undoManager.canUndo();
    }
    public boolean canRedo(){
        return undoManager.canRedo();
    }

    public void undo(){
        try {
            undoManager.undo();
        }catch (CannotUndoException e){
            ExceptionHandler.log(e);
        }
    }
    public void redo(){
        try {
            undoManager.redo();
        }catch (CannotRedoException e){
            ExceptionHandler.log(e);
        }
    }

    public void resetUndo(){
        undoManager.discardAllEdits();
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }

    public boolean isHtmlTabSelected(){
        //System.out.println(tabbedPane.getSelectedIndex() == 0);
        return tabbedPane.getSelectedIndex() == 0;
    }

    public void selectHtmlTab(){
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    public void update(){
        htmlTextPane.setDocument(controller.getDocument());
    }

    public void showAbout(){
        JOptionPane.showMessageDialog(tabbedPane,"О программе. Контент","О программе. Заголовок", JOptionPane.INFORMATION_MESSAGE);
    }

    public View() {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (HeadlessException e){
            ExceptionHandler.log(e);
            //e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            ExceptionHandler.log(e);
            //e.printStackTrace();
        } catch (IllegalAccessException e) {
            ExceptionHandler.log(e);
            //e.printStackTrace();
        } catch (InstantiationException e) {
            ExceptionHandler.log(e);
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            ExceptionHandler.log(e);
            //e.printStackTrace();
        }
    }
}
