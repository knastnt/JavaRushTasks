package com.javarush.task.task23.task2312;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<SnakeSection> sections;
    private boolean isAlive;
    private SnakeDirection direction;

    public List<SnakeSection> getSections() {
        return sections;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public SnakeDirection getDirection() {
        return direction;
    }

    public void setDirection(SnakeDirection direction) {
        this.direction = direction;
    }

    public Snake(int x, int y) {
        sections = new ArrayList<>();
        sections.add(new SnakeSection(x,y));
        setAlive(true);

    }

    public int getX(){
        try{
            return sections.get(0).getX();
        }catch (Exception e){
            return 0;
        }
    }

    public int getY(){
        try{
            return sections.get(0).getY();
        }catch (Exception e){
            return 0;
        }
    }

    public void move(){
        if (!isAlive) return;
        switch (direction){
            case UP:
                move(0, -1);
                break;

            case DOWN:
                move(0, 1);
                break;

            case LEFT:
                move(-1, 0);
                break;

            case RIGHT:
                move(1, 0);
                break;
        }
    }

    public void move(int a, int b){

    }
}
