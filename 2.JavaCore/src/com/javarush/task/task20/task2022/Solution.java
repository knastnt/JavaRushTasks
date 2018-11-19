package com.javarush.task.task20.task2022;

import java.io.*;

/* 
Переопределение сериализации в потоке
*/
public class Solution implements Serializable, AutoCloseable {
    private transient FileOutputStream stream;
    private String fileName;

    //Конструктор
    public Solution(String fileName) throws FileNotFoundException {
        //поток записи в файл, который передали при создании объекта Solution
        this.fileName = fileName;
        this.stream = new FileOutputStream(fileName);
    }

    //записываем строку в свой поток
    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    //Закрываем свой поток вывода в файл
    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }


    //Методы ручного изменения алгоритмов сериализации объекта Solution
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        //out.close();
    }
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        //in.close();
        this.stream = new FileOutputStream(this.fileName, true);
    }


    public static void main(String[] args) throws Exception {


        //Создаём экземпляр класса с потоком вывода в файл
        Solution solution = new Solution("D:/file.txt");
        //говорим объекту записать данные в свой поток
        solution.writeObject("hello, world");


        //сериализуем
        FileOutputStream fos = new FileOutputStream("D:/sol_file.txt");
        ObjectOutputStream ous = new ObjectOutputStream(fos);

        ous.writeObject(solution);

        ous.close();
        fos.close();


        //десериализуем
        FileInputStream fis = new FileInputStream("D:/sol_file.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);

        Solution newSolution = (Solution) ois.readObject();

        ois.close();
        fis.close();


        //говорим объекту записать данные в свой поток
        solution.writeObject("2hello, world2");
        solution.close();
    }
}
