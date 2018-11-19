package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;

import java.util.List;

public interface Model {
    List<User> getAllUsers();
    List<User> getDeletedUsers();
    User getUserById(long userId);
    void deleteUserById(long userId);
    void changeUserData(String name, long id, int level);
}
