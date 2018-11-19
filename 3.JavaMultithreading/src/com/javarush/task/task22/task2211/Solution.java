package com.javarush.task.task22.task2211;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName1 = args[0];
        String fileName2 = args[1];

        /*String fileName1 = "D:/1.txt";
        String fileName2 = "D:/2.txt";*/

        FileInputStream fis = new FileInputStream(fileName1);

        //byte[] readed = fis.readAllBytes();

        int DEFAULT_BUFFER_SIZE = 8192;
        int MAX_BUFFER_SIZE = Integer.MAX_VALUE - 8;

        byte[] buf = new byte[DEFAULT_BUFFER_SIZE];
        int capacity = buf.length;
        int nread = 0;
        int n;
        for (;;) {
            // read to EOF which may read more or less than initial buffer size
            while ((n = fis.read(buf, nread, capacity - nread)) > 0)
                nread += n;

            // if the last call to read returned -1, then we're done
            if (n < 0)
                break;

            // need to allocate a larger buffer
            if (capacity <= MAX_BUFFER_SIZE - capacity) {
                capacity = capacity << 1;
            } else {
                if (capacity == MAX_BUFFER_SIZE)
                    throw new OutOfMemoryError("Required array size too large");
                capacity = MAX_BUFFER_SIZE;
            }
            buf = Arrays.copyOf(buf, capacity);
        }
        byte[] readed = (capacity == nread) ? buf : Arrays.copyOf(buf, nread);



        fis.close();

        String string = new String(readed, "Windows-1251");

        FileOutputStream fos = new FileOutputStream(fileName2);

        fos.write(string.getBytes());

        fos.close();
    }
}
