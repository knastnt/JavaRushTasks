package com.javarush.task.task34.task3404;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Рекурсия для мат. выражения
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.recurse("5*sin(3-2.5*3-0.5)+28 * (5^2)", 0)); //expected output 0.5 6
//        solution.recurse("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6
    }

    public String recurse(final String expression, int countOperation) {
        System.out.println("Вызов: " + expression + "  " + countOperation);

        String cleanExpression = expression.replace(" ", "");
//        Pattern p = Pattern.compile("(.+)\\.part(\\d+)$");
//        Matcher m = p.matcher(testString);
//        //return m.matches();
//        m.find();
//        return m.group(1);
//        Pattern p = Pattern.compile("(^.*)([0-9]+(\\.[0-9]+)?)(\\*|\\^)([0-9]+(\\.[0-9]+)?)(.*$)");
//        Pattern p = Pattern.compile("(^|[^0-9])([0-9]+(\\.[0-9]+)?)($|[^0-9])"); //double
//        Pattern p = Pattern.compile(  "(^|^.*[^0-9\\.])([0-9]+(\\.[0-9]+)?)(\\*|\\^)([0-9]+(\\.[0-9]+)?)($|[^0-9\\.].*$)"  ); //double

        String start = "";
        String end = "";
        double first = 0;
        double second = 0;
        double result = 0;
        String charact = "";

        Pattern p;
        Matcher m;

        p = Pattern.compile(  "(^|^.*\\(|^.*\\d(\\+))(-?[0-9]+(\\.[0-9]+)?)(\\*|\\^)(-?[0-9]+(\\.[0-9]+)?)($|[^0-9\\.].*$)"  ); //double
        m = p.matcher(cleanExpression);
        if(m.find()){
            for (int i=0; i<=m.groupCount(); i++){
//                System.out.println(m.group(i));
            }

            start = m.group(1);
            end = m.group(8);
            first = Double.parseDouble(m.group(3));
            second = Double.parseDouble(m.group(6));
            charact = m.group(5);
        }else{
            p = Pattern.compile(  "(^|^.*\\(|^.*\\d(\\+))(-?[0-9]+(\\.[0-9]+)?)(\\+|\\-)(-?[0-9]+(\\.[0-9]+)?)($|[^0-9\\.].*$)"  ); //double
            m = p.matcher(cleanExpression);
            if(m.find()){
                for (int i=0; i<=m.groupCount(); i++){
//                    System.out.println(m.group(i));
                }

                start = m.group(1);
                end = m.group(8);
                first = Double.parseDouble(m.group(3));
                second = Double.parseDouble(m.group(6));
                charact = m.group(5);
            }else{

                    return expression;

            }
        }




        switch (charact){
            case "*":
                result = first * second;
                break;
            case "^":
                result = Math.pow(first, second);
                break;
            case "+":
                result = first + second;
                break;
            case "-":
                result = first - second;
                break;
        }

        String resultS = String.valueOf(result);

        //return start + resultS + end;
        String res = "";
        if(start.endsWith("-") || start.endsWith("(") || start == "") {
            res = recurse(start + resultS + end, countOperation + 1);
        }else{
            res = recurse(start + "+" + resultS + end, countOperation + 1);
        }
        return res;
    }

    public Solution() {
        //don't delete
    }
}
