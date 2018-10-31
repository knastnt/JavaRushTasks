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

    public void run(){
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        try {
            synchronized (Client.this) {
                wait();
            }
            if(clientConnected){
                ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
            }else{
                ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
            }
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

    public class SocketThread extends Thread{
        protected void processIncomingMessage(String message){
            ConsoleHelper.writeMessage(message);
        }
        protected void informAboutAddingNewUser(String userName){
            ConsoleHelper.writeMessage(userName + " присоединился к чату");
        }
        protected void informAboutDeletingNewUser(String userName){
            ConsoleHelper.writeMessage(userName + " покинул чат");
        }
        protected void notifyConnectionStatusChanged(boolean clientConnected){
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this) {
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException{
            while (!clientConnected){
                Message message = connection.receive();
                if (message.getType() == MessageType.NAME_REQUEST) {
                    connection.send(new Message(MessageType.USER_NAME, getUserName()));
                }else if (message.getType() == MessageType.NAME_ACCEPTED){
                    notifyConnectionStatusChanged(true);
                }else{
                    throw new IOException("Unexpected MessageType");
                }
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException{
            while (true) {
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
                Socket socket = new Socket(getServerAddress(), getServerPort());
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            }catch (IOException | ClassNotFoundException e){
                ConsoleHelper.writeMessage("Error in Client in run");
                notifyConnectionStatusChanged(false);
            }
        }
    }

    protected String getServerAddress(){
        ConsoleHelper.writeMessage("Введите IP адрес сервера:");
        return ConsoleHelper.readString();
    }
    protected int getServerPort(){
        ConsoleHelper.writeMessage("Введите порт сервера:");
        return ConsoleHelper.readInt();
    }
    protected String getUserName(){
        ConsoleHelper.writeMessage("Введите Ваше имя:");
        return ConsoleHelper.readString();
    }
    protected boolean shouldSendTextFromConsole(){
        return true;
    }
    protected SocketThread getSocketThread(){
        return new SocketThread();
    }
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
        new Client().run();
    }
}
