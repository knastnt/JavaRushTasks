package com.javarush.task.task38.task3802;                                                  
                                                  
/*                                                   
Проверяемые исключения (checked exception)                                                  
*/

import java.io.File;
import java.nio.file.Files;

public class VeryComplexClass {
    public void veryComplexMethod() throws Exception {
        //напишите тут ваш код   
        File f = new File("g:/hhhnnnbbb");
        Files.readAllLines(f.toPath());
    }                                                  
                                                  
    public static void main(String[] args) {                                                  
                                                  
    }                                                  
}