package com.javarush.task.task38.task3804;

public class ExceptionFabric {
    public static Throwable getException(Enum anEnum){
        if (anEnum == null) return new IllegalArgumentException();

        String message = (Character.toUpperCase(anEnum.toString().charAt(0)) + anEnum.toString().toLowerCase().substring(1)).replace("_", " ");
        if(anEnum instanceof ApplicationExceptionMessage){
            return new Exception(message);
        }
        if(anEnum instanceof DatabaseExceptionMessage){
            return new RuntimeException(message);
        }
        if(anEnum instanceof UserExceptionMessage){
            return new Error(message);
        }

        return new IllegalArgumentException();
    }
}
