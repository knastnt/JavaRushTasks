package com.javarush.task.task36.task3608.view;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.controller.Controller;

public class EditUserView implements View {
    private Controller controller;
    private User activeUser;

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    @Override
    public void refresh() {
        System.out.println("User to be edited:");
        System.out.println("\t" + activeUser);
        System.out.println("===================================================");
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void fireEventOpenUserEditForm(long id){
        controller.onOpenUserEditForm(id);
    }

    public void fireEventUserDeleted(long id){
        controller.onUserDelete(id);
    }

    public void fireEventUserChanged(String name, long id, int level){
        controller.onUserChange(name, id, level);
    }
}
