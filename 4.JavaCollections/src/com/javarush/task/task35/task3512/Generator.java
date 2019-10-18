package com.javarush.task.task35.task3512;

public class Generator<T> {
    private Class<T> clazz;

    T newInstance() {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Generator(Class<T> clazz) {
        this.clazz = clazz;
    }
}
