package com.javarush.task.task34.task3404;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Рекурсия для мат. выражения
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recurse("(1.5*4.2)", 0); //expected output 0.5 6
//        solution.recurse("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6
    }

    public double recurse(final String expression, int countOperation) {
        String cleanExpression = expression.replace(" ", "");
//        Pattern p = Pattern.compile("(.+)\\.part(\\d+)$");
//        Matcher m = p.matcher(testString);
//        //return m.matches();
//        m.find();
//        return m.group(1);
        Pattern p = Pattern.compile("(^.*)([0-9]+(\\.[0-9]+)?)(\\*|\\^)([0-9]+(\\.[0-9]+)?)(.*$)");
        Matcher m = p.matcher(cleanExpression);
        //return m.matches();
        m.find();
        for (int i=0; i<m.groupCount(); i++){
            System.out.println(m.group(i));
        }
        return 0;
        //implement
    }

    public Solution() {
        //don't delete
    }
}
