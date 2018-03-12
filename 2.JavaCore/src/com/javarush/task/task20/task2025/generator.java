package com.javarush.task.task20.task2025;

public class generator {
    private long N;
    private int NLength;
    private int[] chars;
    private int charsLength = 1;

    public generator(long N) {
        this.N = N;
        NLength = String.valueOf(N).length();

        //Инициируем массив
        String stringN = String.valueOf(N);
        chars = new int[stringN.length()];
        for (int i = 0; i < chars.length; i++) {
            //chars[chars.length - 1 - i] = Character.getNumericValue(stringN.charAt(i));
            chars[chars.length - 1 - i] = 0;
        }
    }

    public int[] getChars(){
        return chars;
    }

    public int getCharsLength(){
        return charsLength;
    }

    public long getPredel(){
        return N;
    }

    public int getPredelLength(){
        return NLength;
    }

    public boolean generateNext(){
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] < 9) {
                chars[i]++;
                for (int k = i - 1; k >= 0; k--) {
                    chars[k] = chars[i];
                }
                break;
            } else {

                if (i == chars.length - 1) {
                    return false;
                } else {
                    if (charsLength < i + 2) {
                        charsLength = i + 2;
                    }
                }
            }
        }
        return true;
    }

    public long getNumber() {
        long S = 0;
        //Печатаем текущее число
        for (int i = 0; i < chars.length; i++) {
            //System.out.print(chars[chars.length - 1 - i]);
            S += chars[chars.length - 1 - i] * match.toStepen(10, chars.length - 1 - i);
        }
        if (S<0) return Long.MAX_VALUE;
        return S;
    }
}
