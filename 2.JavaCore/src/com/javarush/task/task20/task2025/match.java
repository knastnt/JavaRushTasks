package com.javarush.task.task20.task2025;

public class match {
    private static long[][] cache;


    public static void init(long predel){
        cache = new long[11][String.valueOf(predel).length()+2];
    }

    public static long toStepen(int chislo, int stepen){
        long result = cache[chislo][stepen];
        if(result==0){
            //кэша нет
            if(stepen == 0){
                result = 1;
            }else {
                result = (long) chislo;
                for (int i = 1; i < stepen; i++) {
                    result = result * chislo;
                }
            }
            cache[chislo][stepen] = result;
        }
        return result;
    }


    public static long testArmstrong(generator gen){
        int stepen = gen.getCharsLength();
        long previous_result_chislo = 0;
        while (true) {
            long result_chislo = getStepenSumm(gen.getChars(), gen.getCharsLength(), stepen); //сумма цифр числа S, возведенных в M степень
            int length = String.valueOf(result_chislo).length();
            if(length == stepen){
                //Дополнительно проверить его
                if (finalCheckArmstrong(result_chislo, length)) {
                    return result_chislo;
                }
            }
            if(previous_result_chislo == result_chislo || result_chislo >= gen.getPredel() || stepen > gen.getPredelLength()){
                break;
            }
            previous_result_chislo = result_chislo;
            stepen++;
        }
        return  -1;
    }

    public static long testArmstrong(int[] chars, int charsLength, long predel, int predelLength){
        int stepen = charsLength;
        long previous_result_chislo = 0;
        while (true) {
            long result_chislo = getStepenSumm(chars, charsLength, stepen); //сумма цифр числа S, возведенных в M степень
            int length = String.valueOf(result_chislo).length();
            if(length == stepen){
                //Дополнительно проверить его
                if (finalCheckArmstrong(result_chislo, length)) {
                    return result_chislo;
                }
            }
            if(previous_result_chislo == result_chislo || result_chislo >= predel || stepen > predelLength){
                break;
            }
            previous_result_chislo = result_chislo;
            stepen++;
        }
        return  -1;
    }

    public static boolean finalCheckArmstrong(long chislo, int razryadnost){
        return (chislo == getStepenSumm(chislo, razryadnost));
    }

    public static long getStepenSumm(int[] chars, int charsLength, int stepen){
        long result_chislo = 0; //сумма цифр числа S, возведенных в M степень

        /*while(chislo>0) {
            int razryad = (int) (chislo%10);
            chislo = chislo / 10;

            long newChislo = toStepen(razryad,stepen);
            result_chislo += newChislo;
        }*/

        for (int i = 0; i < charsLength; i++) {

            result_chislo += toStepen(chars[i], stepen);
        }

        return result_chislo;
    }

    public static long getStepenSumm(long chislo, int stepen){
        long result_chislo = 0; //сумма цифр числа S, возведенных в M степень

        while(chislo>0) {
            int razryad = (int) (chislo%10);
            chislo = chislo / 10;

            long newChislo = toStepen(razryad,stepen);
            result_chislo += newChislo;
        }

        return result_chislo;
    }
}
