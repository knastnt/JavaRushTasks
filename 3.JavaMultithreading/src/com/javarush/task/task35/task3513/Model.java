package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
    protected int score, maxTile;

    public Model() {
        resetGameTiles();
    }

    public void resetGameTiles() {
        score=0;
        maxTile=0;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    private void addTile(){
        List<Tile> emptyTiles = getEmptyTiles();
        if (emptyTiles.size() == 0) return;
        Tile selectedTile = emptyTiles.get( (int) (Math.random() * emptyTiles.size()) );
        selectedTile.value = Math.random() < 0.9 ? 2 : 4;
    }

    private List<Tile> getEmptyTiles(){
        List<Tile> toReturn = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].value == 0) toReturn.add(gameTiles[i][j]);
            }
        }
        return toReturn;
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean toReturn = false;
        int[] tempTiles = new int[tiles.length];
        int n = 0;
        for (int i = 0; i < tiles.length; i++) {
            if(tiles[i].value != 0) {
                tempTiles[n] = tiles[i].value;
                n++;
            }
        }
        n = tiles.length -1;
        for (int i = tiles.length - 1; i >= 0; i--) {
            if(tiles[i].value == 0) {
                tempTiles[n] = tiles[i].value;
                n--;
            }
        }
        for (int i = 0; i < tempTiles.length; i++) {
            if(tiles[i].value != tempTiles[i]){
                tiles[i].value = tempTiles[i];
                toReturn = true;
            }
        }
        return toReturn;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean toReturn = false;
        for (int i = 0; i < tiles.length; i++) {
            if(i<tiles.length-1 && tiles[i].value != 0 && tiles[i].value == tiles[i+1].value){
                tiles[i].value += tiles[i+1].value;
                tiles[i+1].value = 0;

                score += tiles[i].value;
                if(maxTile<tiles[i].value){maxTile=tiles[i].value;}

                i++;

                toReturn = true;
            }
        }
        compressTiles(tiles);
        return toReturn;
    }

    public void left(){
        boolean changed = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            Tile[] stroka = gameTiles[i];
            changed |= compressTiles(stroka);
            changed |= mergeTiles(stroka);
        }
        if ( changed ) { addTile(); };
    }

    private void turnMassiveCounterclockwise(){
        int[][] tempMass = new int[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                tempMass[i][j] = gameTiles[j][FIELD_WIDTH-i-1].value;
            }
        }

        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j].value = tempMass[i][j];
            }
        }
    }

    public void up(){
        turnMassiveCounterclockwise();
        left();
        turnMassiveCounterclockwise();
        turnMassiveCounterclockwise();
        turnMassiveCounterclockwise();
    }

    public void right(){
        turnMassiveCounterclockwise();
        turnMassiveCounterclockwise();
        left();
        turnMassiveCounterclockwise();
        turnMassiveCounterclockwise();
    }

    public void down(){
        turnMassiveCounterclockwise();
        turnMassiveCounterclockwise();
        turnMassiveCounterclockwise();
        left();
        turnMassiveCounterclockwise();
        System.out.println();
    }
}
