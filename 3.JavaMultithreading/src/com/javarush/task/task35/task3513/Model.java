package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
    protected int score, maxTile;

    private Stack<Tile[][]> previousStates = new Stack<>();
    private Stack<Integer> previousScores = new Stack<>();
    //private boolean isSaveNeeded = true;

    private void saveState(Tile[][] state){
        Tile[][] copyOfGame = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                copyOfGame[i][j] = new Tile(state[i][j].value);
            }
        }
        previousStates.push(copyOfGame);
        previousScores.push(score);
        //isSaveNeeded = false;
    }

    public void rollback(){
        if (!previousStates.empty() && !previousScores.empty()) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }

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
        saveState(gameTiles);
        leftaction();
    }

    private void leftaction(){
       // if(isSaveNeeded) { saveState(gameTiles); }
        boolean changed = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            Tile[] stroka = gameTiles[i];
            changed |= compressTiles(stroka);
            changed |= mergeTiles(stroka);
        }
        if ( changed ) { addTile(); };
    }

    private void turnMassive(){
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
        saveState(gameTiles);
        turnMassive();
        leftaction();
        turnMassive();
        turnMassive();
        turnMassive();
    }

    public void right(){
        saveState(gameTiles);
        turnMassive();
        turnMassive();
        leftaction();
        turnMassive();
        turnMassive();
    }

    public void down(){
        saveState(gameTiles);
        turnMassive();
        turnMassive();
        turnMassive();
        leftaction();
        turnMassive();
    }

    public boolean canMove(){
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].value == 0) return true;
                if (i<FIELD_WIDTH-1 && gameTiles[i][j].value == gameTiles[i+1][j].value) return true;
                if (j<FIELD_WIDTH-1 && gameTiles[i][j].value == gameTiles[i][j+1].value) return true;
            }
        }
        return false;
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public void randomMove(){
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n){
            case 1:
                left();
                break;
            case 2:
                right();
                break;
            case 3:
                up();
                break;
            case 4:
                down();
                break;
        }
    }

    private boolean hasBoardChanged(){
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].value != previousStates.peek()[i][j].value) { return true; }
            }
        }
        return false;
    }

    private MoveEfficiency getMoveEfficiency(Move move){
        int oldNumberOfEmptyTiles = getEmptyTiles().size();
        int oldScope = score;

        move.move();
        MoveEfficiency me;
        if(!hasBoardChanged()) {
            me = new MoveEfficiency(-1,0,move);
        }else{
            me = new MoveEfficiency(getEmptyTiles().size(),score,move);
        }
        rollback();
        return me;
    }

    public void autoMove(){
        PriorityQueue pq = new PriorityQueue(4,Collections.reverseOrder());
        pq.offer(getMoveEfficiency(() -> left()));
        pq.offer(getMoveEfficiency(() -> right()));
        pq.offer(getMoveEfficiency(() -> down()));
        pq.offer(getMoveEfficiency(() -> up()));

        ((MoveEfficiency)pq.poll()).getMove().move();
    }
}
