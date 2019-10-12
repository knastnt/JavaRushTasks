package com.javarush.task.task34.task3404;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Рекурсия для мат. выражения
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.recurse("5*sin(1.7754*5.2)+28", 0)); //expected output 0.5 6
//        solution.recurse("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6
    }

    public String recurse(final String expression, int countOperation) {
        String cleanExpression = expression.replace(" ", "");
//        Pattern p = Pattern.compile("(.+)\\.part(\\d+)$");
//        Matcher m = p.matcher(testString);
//        //return m.matches();
//        m.find();
//        return m.group(1);
//        Pattern p = Pattern.compile("(^.*)([0-9]+(\\.[0-9]+)?)(\\*|\\^)([0-9]+(\\.[0-9]+)?)(.*$)");
//        Pattern p = Pattern.compile("(^|[^0-9])([0-9]+(\\.[0-9]+)?)($|[^0-9])"); //double
//        Pattern p = Pattern.compile(  "(^|^.*[^0-9\\.])([0-9]+(\\.[0-9]+)?)(\\*|\\^)([0-9]+(\\.[0-9]+)?)($|[^0-9\\.].*$)"  ); //double
        Pattern p = Pattern.compile(  "(^|^.*[^0-9\\.])([0-9]+(\\.[0-9]+)?)(\\*|\\^)([0-9]+(\\.[0-9]+)?)($|[^0-9\\.].*$)"  ); //double
        Matcher m = p.matcher(cleanExpression);
        //return m.matches();
        m.find();
        for (int i=0; i<=m.groupCount(); i++){
//            System.out.println(m.group(i));
        }
        String start = m.group(1);
        String end = m.group(7);
        double first = Double.parseDouble(m.group(2));
        double second = Double.parseDouble(m.group(5));
        double result = 0;
        String charact = m.group(4);

        switch (charact){
            case "*":
                result = first * second;
                break;
        }

        String resultS;
        if(result<0){
            resultS = "0-" + String.valueOf(result);
        }else {
            resultS = String.valueOf(result);
        }
        return start + resultS + end;
        //implement
    }

    public Solution() {
        //don't delete
    }
}
