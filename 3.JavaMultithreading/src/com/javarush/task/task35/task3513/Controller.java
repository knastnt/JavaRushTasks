package com.javarush.task.task35.task3513;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {
    private Model model;
    private View view;
    private static final int WINNING_TILE = 2048;

    public Tile[][] getGameTiles(){
        return model.getGameTiles();
    }

    public Controller(Model model) {
        this.model = model;
        view = new View(this);
    }

    public int getScore(){
        return model.score;
    }

    public void resetGame(){
        view.isGameWon = false;
        view.isGameLost = false;
        model.score = 0;
        model.resetGameTiles();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if ( e.getKeyCode() == KeyEvent.VK_ESCAPE ) { resetGame(); }

        view.isGameLost = !model.canMove();

        if ( !view.isGameWon && !view.isGameLost ) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    model.left();
                    break;
                case KeyEvent.VK_RIGHT:
                    model.right();
                    break;
                case KeyEvent.VK_UP:
                    model.up();
                    break;
                case KeyEvent.VK_DOWN:
                    model.down();
                    break;
            }
        }

        if (model.maxTile == WINNING_TILE) { view.isGameWon = true; }

        view.repaint();
    }
}
