package com.javarush.task.task32.task3209;

import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.File;

public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }

    public void init(){

    }

    public void exit(){
        System.exit(0);
    }

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }

    public HTMLDocument getDocument() {
        return document;
    }

    /*public void resetDocument(){
        if(getDocument() != null){
            document.removeUndoableEditListener(view.getUndoListener());
        }
        document = (HTMLDocument)(new HTMLEditorKit()).createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }*/

    // удаляет существующий документ и создает пустой
    public void resetDocument() {
        if (document != null) {
            // удаляет существующий документ
            document.removeUndoableEditListener(view.getUndoListener());
        }
        // создает документ по умолчанию
        document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }
}
