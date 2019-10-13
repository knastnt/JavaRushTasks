package com.javarush.task.task34.task3404;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Рекурсия для мат. выражения
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recurse("5*sin(3-2.5*3-0.5)+28*(5^2)", 0); //expected output 0.5 6
//        solution.recurse("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6
    }

    //Это надо решать с помощью обратной польской записи
    //https://www.semestr.online/informatics/polish.php
    //https://habr.com/ru/post/100869/
    //https://ru.stackoverflow.com/questions/643449/%D0%9A%D0%B0%D0%BA-%D0%BB%D1%83%D1%87%D1%88%D0%B5-%D0%BF%D0%B0%D1%80%D1%81%D0%B8%D1%82%D1%8C-%D0%BC%D0%B0%D1%82%D0%B5%D0%BC%D0%B0%D1%82%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%BE%D0%B5-%D0%B2%D1%8B%D1%80%D0%B0%D0%B6%D0%B5%D0%BD%D0%B8%D0%B5

    //Но х.з. куда там рекурсию прицепить

    public void recurse(final String expression, int countOperation) {
        System.out.println("Вызов: " + expression + "  " + countOperation);
        String cleanExpression = expression.replace(" ", "");
        Stack<String> stack = new Stack<>();

        String tmpBuffer = "";
        for(int i = 0; i < cleanExpression.length(); i++){
            Character character = expression.charAt(i);
            if(Character.isDigit(character) || character == '.'){
                tmpBuffer += character;
            }else{
                if(tmpBuffer != "") {
                    stack.push(tmpBuffer);
                    tmpBuffer = "";
                }
                stack.push(character.toString());
            }
        }
        if(tmpBuffer != "") {
            stack.push(tmpBuffer);
            tmpBuffer = "";
        }

    }

    public Solution() {
        //don't delete
    }
}
