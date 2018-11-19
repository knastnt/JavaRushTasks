package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.model.service.UserService;
import com.javarush.task.task36.task3608.model.service.UserServiceImpl;

import java.util.List;

public class MainModel implements Model {
    private UserService userService = new UserServiceImpl();

    @Override
    public List<User> getAllUsers(){
        return userService.filterOnlyActiveUsers(userService.getUsersBetweenLevels(1,100));
    }

    @Override
    public List<User> getDeletedUsers() {
        return userService.getAllDeletedUsers();
    }

    @Override
    public User getUserById(long userId) {
        return userService.getUsersById(userId);
    }

    @Override
    public void deleteUserById(long userId) {
        userService.deleteUser(userId);
    }

    @Override
    public void changeUserData(String name, long id, int level) {
        userService.createOrUpdateUser(name, id, level);
    }
}
