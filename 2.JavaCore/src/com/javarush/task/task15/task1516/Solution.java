package com.javarush.task.task15.task1516;

/* 
Значения по умолчанию
*/

public class Solution {
    //static{
        public int intVar;// типа int
        public double doubleVar;// типа double
        public Double DoubleVar;// типа Double
        public boolean booleanVar;// типа boolean
        public Object ObjectVar;// типа Object
        public Exception ExceptionVar;// типа Exception
        public String StringVar;// типа String
    //}
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.intVar);// типа int
        System.out.println(s.doubleVar);// типа double
        System.out.println(s.DoubleVar);// типа Double
        System.out.println(s.booleanVar);// типа boolean
        System.out.println(s.ObjectVar);// типа Object
        System.out.println(s.ExceptionVar);// типа Exception
        System.out.println(s.StringVar);// типа String
    }
}
