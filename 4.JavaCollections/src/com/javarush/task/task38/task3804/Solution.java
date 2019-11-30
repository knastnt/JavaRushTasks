package com.javarush.task.task38.task3804;

/* 
Фабрика исключений
*/

public class Solution {
    public static Class getFactoryClass() {
        return ExceptionFabric.class;
    }

    public static void main(String[] args) {
        ExceptionFabric.getException(ApplicationExceptionMessage.SOCKET_IS_CLOSED);
    }
}