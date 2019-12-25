package com.javarush.task.task39.task3909;

import java.util.*;

/*
Одно изменение
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isOneEditAway("kghdvrfd", "kghd12rfd"));
    }

    public static boolean isOneEditAway(String first, String second) {
        if (first == null || second == null) return false;
        if (first.equals(second)) return true;
        if (Math.abs(first.length()-second.length()) > 1) return false;
        if (first.isEmpty() || second.isEmpty()) return true;

//        char[] firstC = first.toCharArray();
//        char[] secondC = second.toCharArray();
//
//        int errorsCount = 0;
//        if (firstC.length == secondC.length){
//            for (int i = 0; i < firstC.length; i++) {
//                if (firstC[i] != secondC[i]){
//                    errorsCount++;
//                }
//            }
//        }
//        if (firstC.length > secondC.length){
//            int j = 0;
//            for (int i = 0; i < firstC.length; i++) {
//                if (firstC[i] != secondC[j]){
//                    errorsCount++;
//                }else{
//                    j++;
//                }
//            }
//        }
//        if (firstC.length < secondC.length){
//            int j = 0;
//            for (int i = 0; i < secondC.length; i++) {
//                if (secondC[i] != firstC[j]){
//                    errorsCount++;
//                }else{
//                    j++;
//                }
//            }
//        }
//        return errorsCount==1;
        return levenstain(first, second) == 1;
    }


    public static int levenstain(String str1, String str2) {
        int[] Di_1 = new int[str2.length() + 1];
        int[] Di = new int[str2.length() + 1];

        for (int j = 0; j <= str2.length(); j++) {
            Di[j] = j; // (i == 0)
        }

        for (int i = 1; i <= str1.length(); i++) {
            System.arraycopy(Di, 0, Di_1, 0, Di_1.length);

            Di[0] = i; // (j == 0)
            for (int j = 1; j <= str2.length(); j++) {
                int cost = (str1.charAt(i - 1) != str2.charAt(j - 1)) ? 1 : 0;
                Di[j] = min(
                        Di_1[j] + 1,
                        Di[j - 1] + 1,
                        Di_1[j - 1] + cost
                );
            }
        }

        return Di[Di.length - 1];
    }

    private static int min(int n1, int n2, int n3) {
        return Math.min(Math.min(n1, n2), n3);
    }
}
