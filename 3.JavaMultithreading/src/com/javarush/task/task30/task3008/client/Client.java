package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;

    public void run(){ //Нет, это не трэд

        //запуск клиента

        //Создаем трэд
        SocketThread socketThread = getSocketThread();
        //Делаем его демоном
        socketThread.setDaemon(true);
        //Запускаем
        socketThread.start();
        try {
            //Ждём пока нас разбудят чтобы продолжить.
            //Это произойдёт после установлени соединения с сервером
            synchronized (Client.this) {
                wait();
            }
            if(clientConnected){
                ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
            }else{
                ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
            }
            //Пока соединение есть, шлём набранные сообщения в сеть
            while (clientConnected){
                String inputted = ConsoleHelper.readString();
                if(inputted.equals("exit")) break;
                if(shouldSendTextFromConsole()){
                    sendTextMessage(inputted);
                }
            }
        } catch (InterruptedException e) {
            //e.printStackTrace();
            ConsoleHelper.writeMessage("InterruptedException while client wating");
        }
    }

    //Это трэд
    public class SocketThread extends Thread{
        //Метод отображения входящего текстового сообщения
        protected void processIncomingMessage(String message){
            ConsoleHelper.writeMessage(message);
        }

        //Метод информирования о подключения нового пользователя
        protected void informAboutAddingNewUser(String userName){
            ConsoleHelper.writeMessage(userName + " присоединился к чату");
        }

        //Метод информирования об отключения пользователя
        protected void informAboutDeletingNewUser(String userName){
            ConsoleHelper.writeMessage(userName + " покинул чат");
        }

        //Метод который изменит флаг подключения и разбудит основной поток при подключении
        protected void notifyConnectionStatusChanged(boolean clientConnected){
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this) {
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException{
            //циклим пока ещё соединение не установлено
            while (!clientConnected){
                //Ждем сообщение
                Message message = connection.receive();

                //Сообщение получено
                if (message.getType() == MessageType.NAME_REQUEST) {
                    //Если получен запрос имени
                    connection.send(new Message(MessageType.USER_NAME, getUserName()));
                }else if (message.getType() == MessageType.NAME_ACCEPTED){
                    //Если получено сообщение о регистрации юзера, меняем статус подключения, будим главный поток
                    notifyConnectionStatusChanged(true);
                }else{
                    throw new IOException("Unexpected MessageType");
                }
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException{
            while (true) {
                //Обрабатываем полученные сообщения в бесконечном цикле
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    processIncomingMessage(message.getData());
                }else if (message.getType() == MessageType.USER_ADDED) {
                    informAboutAddingNewUser(message.getData());
                }else if (message.getType() == MessageType.USER_REMOVED) {
                    informAboutDeletingNewUser(message.getData());
                }else{
                    throw new IOException("Unexpected MessageType");
                }
            }
        }

        @Override
        public void run() {
            try {
                //Создаем сокет использую ввод пользователя
                Socket socket = new Socket(getServerAddress(), getServerPort());

                //присваиваем глобальной переменной занчение коннекшена
                connection = new Connection(socket);

                //Ждём и принимаем рукопожатие
                clientHandshake();

                //Переходим к приему сообщений (бесконечный цикл)
                clientMainLoop();
            }catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
                ConsoleHelper.writeMessage("Error in Client in run");
                notifyConnectionStatusChanged(false);
            }
        }
    }

    //метод для получения от пользователя IP адреса для подключения
    protected String getServerAddress(){
        ConsoleHelper.writeMessage("Введите IP адрес сервера:");
        return ConsoleHelper.readString();
    }

    //метод для получения от пользователя порта для подключения
    protected int getServerPort(){
        ConsoleHelper.writeMessage("Введите порт сервера:");
        return ConsoleHelper.readInt();
    }

    //метод для получения от пользователя его имени
    protected String getUserName(){
        ConsoleHelper.writeMessage("Введите Ваше имя:");
        return ConsoleHelper.readString();
    }

    //Х.З. видимо что-то для свинга
    protected boolean shouldSendTextFromConsole(){
        return true;
    }

    //Дурацкий по-моему метод получения объекта класса SocketThread
    protected SocketThread getSocketThread(){
        return new SocketThread();
    }

    //Метод отправки текстового сообщения в сеть
    protected void sendTextMessage(String text){
        try{
            Message message = new Message(MessageType.TEXT, text);
            connection.send(message);
        }catch (IOException e){
            ConsoleHelper.writeMessage("Error in sendTextMessage");
            clientConnected = false;
        }
    }

    public static void main(String[] args) {
        //Создаем нового клиента и инициируем его
        new Client().run(); //Нет, это не трэд
    }
}
