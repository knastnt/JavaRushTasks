package com.javarush.task.task30.task3012;
import java.math.*;
/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(1234);
    }

    public void createExpression(int number) {
        //напишите тут ваш код
        String three = fn(String.valueOf(number));
        if (three.endsWith("2")) three += "1";
        //System.out.println(three);
        String echo = number + " =";
        for(int i=0; i < three.length(); i++) {
           int k = Integer.valueOf(three.substring(i, i+1));
           if(k==1) echo += " + ";
           if(k==2) echo += " - ";
           if(k==0) continue;
           echo += th(i);
        }
        System.out.println(echo);  
        
    }

    public String fn (String n) {
       int in = Integer.valueOf(n);
       if(in<3) return String.valueOf(n);
       int ost = in % 3;
       int res = in / 3;
       if(ost==2) res++;
       return ost+fn(String.valueOf(res));
    }

public int th(int stepen){
 int toReturn = 1;
 for(int i=0; i<stepen; i++){
  toReturn *= 3;
 }
 return toReturn;
}
}