package com.javarush.task.task17.task1721;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    static {
        String f1 = null, f2 = null;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            f1 = reader.readLine();
            f2 = reader.readLine();
            reader.close();
        } catch (IOException e) {
            System.out.println("FilePath error");
        }

        try {
            BufferedReader fr1 = new BufferedReader(new FileReader(new File(f1)));
            BufferedReader fr2 = new BufferedReader(new FileReader(new File(f2)));
            while (fr1.ready()) {
                allLines.add(fr1.readLine());
            }
            while (fr2.ready()) {
                forRemoveLines.add(fr2.readLine());
            }
            fr1.close();
            fr2.close();
        } catch (IOException e) {
            System.out.println("FileReader error!");
        }

    }

    public static void main(String[] args) throws CorruptedDataException {
        new Solution().joinData();
    }

    public synchronized void joinData() throws CorruptedDataException {
        for (int i = 0; i < forRemoveLines.size(); i++) {
            if (allLines.contains(forRemoveLines.get(i))) {
                allLines.remove(forRemoveLines.get(i));
            } else {
                allLines.clear();
                throw new CorruptedDataException();
            }
        }
    }
}