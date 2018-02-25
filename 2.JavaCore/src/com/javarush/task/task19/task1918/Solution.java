package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/



import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws IOException {

        String tag = args[0];

        String fileName = readLineFromConsole();

        //fileName = "D:/1.txt";
        //String tag = "span";

        String fileContent = getFileText(fileName);

        int[][] map = getIns(fileContent, tag);

        String[] arr = extractStrings(fileContent, map);

        for(String ins : arr){
            System.out.println(ins);
        }

    }

    private static String readLineFromConsole() throws IOException {
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        String fileName = brdr.readLine();
        brdr.close();
        return fileName;
    }

    private static String getFileText(String fileName) throws IOException{
        FileReader fr = new FileReader(fileName);
        StringBuffer stb = new StringBuffer();
        char[] buffer = new char[3];
        while (fr.ready()){
            int rCount = fr.read(buffer);
            stb.append(buffer, 0, rCount);
        }
        fr.close();

        return stb.toString();
    }

    private static int[][] getIns(String text, String tag){
        HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();
        //true - open; false - close
        int i = -1;
        while(true) {
            i = text.indexOf("<" + tag, i+1);
            if(i != -1){
                map.put(i, true);
            }else{
                break;
            }
        }

        i = -1;
        while(true) {
            i = text.indexOf("</" + tag + ">", i+1);
            if(i != -1){
                map.put(i + tag.length() + 2, false);
            }else{
                break;
            }
        }

        Object[] arr = map.keySet().toArray();
        Arrays.sort(arr);

        int[][] resArr = new int[arr.length][2];

        for(int k=0; k<arr.length; k++){
            resArr[k][0] = (int)arr[k];
            if(map.get(arr[k]).booleanValue()){
                resArr[k][1] = 1;
            }else{
                resArr[k][1] = 0;
            }
        }

        return resArr;
    }

    private static String[] extractStrings(String content, int[][] map) {
        String[] resArr = new String[map.length/2];
        int resArrLastI = 0;

        for(int i=0; i<map.length;){

            if(map[i][1] == 1){
                //open
                //ищем закрывающий
                int correction = 0;
                for(int j=i+1; j<map.length; j++){
                    if(map[j][1] == 1){
                        //Открывающий
                        correction++;
                    }else{
                        //Закрывающий
                        if(correction==0){
                            //Это нужный закрывающий
                            resArr[resArrLastI] = content.substring(map[i][0],map[j][0]+1);
                            resArrLastI++;
                            break;
                        }else{
                            correction--;
                        }
                    }
                }

            }else{
                //close

            }

            i++;
        }


        return resArr;
    }


    /*
    private static String[] extractStrings(String content, HashMap<Integer, Boolean> map){
        //ArrayList<String> al = new ArrayList<String>();
        String[] resultArr = new String[map.size()/2];
        int resultArrLastIndex = 0;
        Object[] arr = map.keySet().toArray();
        Arrays.sort(arr);

        int begin = 0;
        int laststart = -2;
        while (begin<arr.length-1) {


            int start = -1;
            int stop = -1;
            int correction = 0;
            for (int j = begin; j < arr.length; j++) {
                int i = (int) arr[j];
                if (map.get(i).booleanValue() == true) {
                    //open

                        if (start == -1) {
                            start = i;
                        } else {
                            correction++;
                        }

                } else {
                    //close
                    if (correction == 0) {
                        if(start != -1) {
                            stop = i;

                            if(laststart != start) {
                                laststart = start;
                                //al.add(start + "-" + stop);
                                resultArr[resultArrLastIndex] = content.substring(start, stop+1);
                                resultArrLastIndex++;
                            }
                            begin++;
                            break;
                        }
                    } else {
                        correction--;
                    }
                }
            }
        }

        return resultArr;
    }*/
}
