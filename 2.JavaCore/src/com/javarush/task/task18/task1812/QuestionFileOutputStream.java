package com.javarush.task.task18.task1812;

import java.io.*;

/* 
Расширяем AmigoOutputStream
*/

public class QuestionFileOutputStream implements AmigoOutputStream {
    private AmigoOutputStream ais;

    public QuestionFileOutputStream(AmigoOutputStream ais) {
        this.ais = ais;
    }

    @Override

    public void flush() throws IOException {
        ais.flush();
    }

    @Override
    public void write(int b) throws IOException {
        ais.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        ais.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        ais.write(b, off, len);
    }

    @Override
    public void close() throws IOException {
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Вы действительно хотите закрыть поток? Д/Н");
        if("Д".equals(brdr.readLine())){
            ais.close();
        }
        brdr.close();
    }
}

