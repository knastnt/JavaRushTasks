package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {
    private static final String MESSAGE_ARRAY_EMPTY = "Массив пустой!";

    public static void main(String[] args) throws Exception {
        Integer[] mass = {16, 2, 4, 128, 1, 64, 32, 8};
        for (Integer integer : sort(mass)) {
            //System.out.println(integer);
        }
    }

    public static Integer[] sort(Integer[] array) throws Exception {
        //implement logic here

        Comparator<Integer> compareWithMediane = new Comparator<Integer>() {
            double m = getMediane(array);
            {
                //System.out.println("Mediane: " + m);
            }

            @Override
            public int compare(Integer o1, Integer o2) {
                return (int)(Math.abs(o1-m)-Math.abs(o2-m));
            }
        };
        Arrays.sort(array, compareWithMediane);
        return array;
    }


    private static double getMediane(Integer[] array) throws Exception {
        if(array == null || array.length == 0) throw new Exception(MESSAGE_ARRAY_EMPTY);

        //Integer[] arrayCopy = array.clone();
        Integer[] arrayCopy = array;
        Arrays.sort(arrayCopy);
        if(arrayCopy.length%2 == 1){
            return arrayCopy[arrayCopy.length/2];
        }else{
            return ((double) arrayCopy[arrayCopy.length/2 - 1] + arrayCopy[arrayCopy.length/2])/2;
        }
    }

}
