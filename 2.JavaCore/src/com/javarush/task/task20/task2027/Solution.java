package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };


        /*test(crossword, "home", 5, 3);
        test(crossword, "same", 1, 1);*/
        List l = detectAllWords(crossword, "home", "same");
        System.out.println(l);
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        ArrayList<Word> wl = new ArrayList<Word>();

        for (String word : words) {
            //Перебираем слова
            int firstChar = word.charAt(0);
            //Перебираем crossword в поисках всех первых символов
            for(int i = 0; i<crossword.length; i++){
                for(int j = 0; j<crossword[0].length; j++){
                    if(crossword[i][j] == firstChar){
                        Vektor[] v = test(crossword, word, i, j);
                        if(v.length > 0){
                            for (int k = 0; k < v.length; k++) {
                                Word wordObject = new Word(word);
                                wordObject.startY = i;
                                wordObject.startX = j;
                                wordObject.endY = i+v[k].X*(word.length()-1);
                                wordObject.endX = j+v[k].Y*(word.length()-1);
                                wl.add(wordObject);
                            }

                        }
                    }
                }
            }
        }

        return wl;
    }

    private static class Vektor{
        public int X;
        public int Y;
        public boolean flag;

        public Vektor(int x, int y) {
            X = x;
            Y = y;
            flag = true;
        }
    }

    public static class Vektors extends ArrayList<Vektor>{
        {
            this.add(new Vektor(1,0));
            this.add(new Vektor(1,1));
            this.add(new Vektor(0,1));
            this.add(new Vektor(-1,1));
            this.add(new Vektor(-1,0));
            this.add(new Vektor(-1,-1));
            this.add(new Vektor(0,-1));
            this.add(new Vektor(1,-1));
        }
    }

    public static Vektor[] test(int[][] crossword, String word, int StartX, int StartY){
        int wordLen = word.length();
        int crosswordXmax = crossword[0].length-1;
        int crosswordYmax = crossword.length-1;

        ArrayList<Vektor> resList = new ArrayList<Vektor>();
        Vektor[] returned;
        Vektors vs = new Vektors();
        for(int i = 0; i < 8; i++){
            //Проверяем по размерности кроссворда
            if((vs.get(i).X * (StartX + vs.get(i).X * (wordLen-1)) > crosswordXmax * (vs.get(i).X+1)/2 * vs.get(i).X ) || (vs.get(i).Y * (StartY + vs.get(i).Y * (wordLen-1)) > crosswordYmax * (vs.get(i).Y+1)/2 * vs.get(i).Y )){
                vs.get(i).flag = false;
            }else {
                // Данное направление по размерности канает, проверяем по буквам
                for (int j = 1; j < wordLen; j++){
                    int currentChar = word.charAt(j);
                    if(currentChar != crossword[StartX+vs.get(i).X*j][StartY+vs.get(i).Y*j]){
                        vs.get(i).flag = false;
                        break;
                    }
                }
            }
            if(vs.get(i).flag){
                resList.add(vs.get(i));
            }
        }

        if(resList.size() == 0){
            return null;
        }else{
            returned = new Vektor[resList.size()];
            for (int i = 0; i < resList.size(); i++) {
                returned[i] = resList.get(i);
            }
            return returned;
        }


    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
