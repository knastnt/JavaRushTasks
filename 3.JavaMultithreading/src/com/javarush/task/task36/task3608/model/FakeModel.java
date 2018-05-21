package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;

import java.util.ArrayList;
import java.util.List;

public class FakeModel implements Model {
    private ModelData modelData = new ModelData();

    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUsers() {
        List users = new ArrayList<User>();
        users.add(new User("A", 1, 1));
        users.add(new User("B", 2, 1));
        modelData.setUsers(users);
    }

    @Override
    public void loadDeletedUsers() {
        //this class not uses
        throw new UnsupportedOperationException();
    }

    @Override
    public void loadUserById(long userId) {
        //this class not uses
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteUserById(long userId) {
        //this class not uses
        throw new UnsupportedOperationException();
    }

    @Override
    public void changeUserData(String name, long id, int level) {
        //this class not uses
        throw new UnsupportedOperationException();
    }
}