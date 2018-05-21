package com.javarush.task.task36.task3608;

import com.javarush.task.task36.task3608.controller.Controller;
import com.javarush.task.task36.task3608.model.FakeModel;
import com.javarush.task.task36.task3608.model.MainModel;
import com.javarush.task.task36.task3608.model.Model;
import com.javarush.task.task36.task3608.view.EditUserView;
import com.javarush.task.task36.task3608.view.UsersView;

public class Solution {
    public static void main(String[] args) {
        /*

        Согласно написанному здесь: https://habr.com/post/321050/, а именно "Из-за того, что разработчики не всегда
        хорошо понимают что стоит за всеми этими «моделями», а сами модели привыкли воспринимать как данные а не
        интерфейс, то это становится источником еще одной весьма распространенной и ресурсоемкой ошибки. Вместо того
        чтобы нужным образом всего лишь интерпретировать и адаптировать имеющиеся доменные данные с помощью
        моделей-посредников их начинают копировать в эти модели-посредники. ", мы имеем ту самую ошибку:

        private List<User> users;

        в классе ModelData.
        Получается что нужно изменять данные в базе данных и дополнительно в этом списке:

        public void changeUserData(String name, long id, int level) {
                userService.createOrUpdateUser(name, id, level);
                modelData.setUsers(getAllUsers());
            }

        Кроме того, при совместном использовании БД, записи в листе модели обновляться не будут. Я прав или
        окончательно запутался?

        */

        Model model = new MainModel();
        UsersView usersView = new UsersView();
        EditUserView editUserView = new EditUserView();
        Controller controller = new Controller();



        usersView.setController(controller);
        editUserView.setController(controller);

        controller.setModel(model);

        controller.setUsersView(usersView);
        controller.setEditUserView(editUserView);



        usersView.fireEventShowAllUsers();
        editUserView.fireEventOpenUserEditForm(126);
        editUserView.fireEventUserDeleted(124L);
        editUserView.fireEventUserChanged("Vasya", 125, 5);
        usersView.fireEventShowDeletedUsers();
    }
}