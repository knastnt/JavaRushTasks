package com.javarush.task.task36.task3608.view;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.controller.Controller;

import java.util.List;

public class UsersView implements View {
    private Controller controller;
    private String header;
    private List<User> userList;


    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void refresh() {
        System.out.println(header);
        printUserList();
        System.out.println("===================================================");
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }


    public void fireEventShowAllUsers(){
        header = "All users:";
        controller.onShowAllUsers();
    }

    public void fireEventShowDeletedUsers(){
        header = "All deleted users:";
        controller.onShowAllDeletedUsers();
    }


    private void printUserList(){
        for (User user : userList) {
            System.out.println("\t" + user.toString());
        }
    }
}
