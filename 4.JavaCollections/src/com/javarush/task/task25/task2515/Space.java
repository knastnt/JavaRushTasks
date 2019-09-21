package com.javarush.task.task25.task2515;
                                                                                                                                                                                                        

                                                                                           
import java.util.*;                                                  
                                                                                                                                                               
public class Space {
   public static Space game;
                                                                                                                                                           
   private int width;                                                                                                          
   private int height;                                                                                                    
   private SpaceShip ship;                                                                                                    
   private List<Ufo> ufos = new ArrayList<>();                                                                                                    
   private List<Rocket> rockets = new ArrayList<>();                                                                                                    
   private List<Bomb> bombs = new ArrayList<>();
                                                                                                    
   public void setShip(SpaceShip ship){                                                                                                    
      this.ship = ship;                                                                                                    
   }                                                                                                    
                                                                                                    
   public SpaceShip getShip(){                                                                                                    
      return this.ship;                                                                                                    
   }                                                                                                    

   public int getWidth(){
      return this.width;
   }

   public int getHeight() {
      return this.height;
   }

   public List<Ufo> getUfos() {
      return this.ufos;
   }

   public List<Rocket> getRockets() {
      return this.rockets;
   }

   public List<Bomb> getBombs() {
   return this.bombs;
   }

   public Space(int width, int height){
      this.width=width;
      this.height=height;
   }

   public void run(){

   }

   public void draw(){

   }

   public void sleep(int ms){

   }
                                                                                                                                                                                             
    public static void main(String[] args) {
                                                                                                                                                                                                        

                                                                                                                                                                                                        
    }
                                                                                                                                                                                                        
}