package com.javarush.task.task22.task2201;

public class TooShortStringSecondThreadException extends RuntimeException {
    public TooShortStringSecondThreadException(String message) {
        super(message);
    }
}
