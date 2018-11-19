package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    public static Hippodrome game;

    private List<Horse> horses;

    public List<Horse> getHorses() {
        return horses;
    }

    public void addHorse(Horse horse){
        horses.add(horse);
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public void run() throws InterruptedException {
        for(int i = 1; i<=100; i++){
            move();
            print();
            Thread.sleep(200);
        }
    }

    protected void move(){
        for (Horse horse : horses) {
            horse.move();
        }
    }

    protected void print(){
        for (Horse horse : horses) {
            horse.print();
        }
        for(int i =0; i<10; i++){
            System.out.println("");
        }
    }

    protected Horse getWinner(){
        if (horses.size()==0) return null;

        Horse winner = horses.get(0);

        for (Horse horse : horses) {
            if(winner.getDistance() < horse.getDistance()) winner = horse;
        }

        return winner;
    }

    protected void printWinner(){
        System.out.println("Winner is " + getWinner().getName() + "!");
    }

    public static void main(String[] args) throws InterruptedException {
        game = new Hippodrome(new ArrayList<>());


        game.addHorse(new Horse("Папа", 3, 0));
        game.addHorse(new Horse("Мама", 3, 0));
        game.addHorse(new Horse("Дочка", 3, 0));


        game.run();
        game.printWinner();
    }
}
