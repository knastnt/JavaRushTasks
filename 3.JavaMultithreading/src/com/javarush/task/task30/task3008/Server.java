package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.javarush.task.task30.task3008.MessageType.*;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        ConsoleHelper.writeMessage("Введите порт сервера:");
        int port = ConsoleHelper.readInt();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("сервер запущен");
            while (true) {
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        }catch (IOException e){
            System.out.println("Error while server accepting connections");
            e.printStackTrace();
        }finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    System.out.println("Error when closing serverSocket");
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Handler extends Thread{
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException{
            connection.send(new Message(NAME_REQUEST));
            while (true){
                Message message = connection.receive();
                if(message.getType() != USER_NAME){
                    connection.send(new Message(NAME_REQUEST));
                    continue;
                }

                if(message.getData() != null && !message.getData().equals("") && !connectionMap.containsKey(message.getData())){
                    connectionMap.put(message.getData(), connection);
                    connection.send(new Message(NAME_ACCEPTED));
                    return message.getData();
                }else{
                    //return serverHandshake(connection);
                    connection.send(new Message(NAME_REQUEST));
                    continue;
                }

            }
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException{
            for(Map.Entry<String, Connection> entry : connectionMap.entrySet()){
                if(!entry.getKey().equals(userName)){
                    connection.send(new Message(USER_ADDED, entry.getKey()));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException{
            while (true){
                Message message = connection.receive();
                if(message.getType() == MessageType.TEXT){
                    String string = userName + ": " + message.getData();
                    sendBroadcastMessage(new Message(MessageType.TEXT,string));
                }else{
                    ConsoleHelper.writeMessage("The message no TEXT");
                }
            }
        }

        @Override
        public void run() {
            ConsoleHelper.writeMessage("Connected with " + socket.getRemoteSocketAddress());
            Connection connection = null;
            String userName = "";
            try {
                connection = new Connection(socket);
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection, userName);
                serverMainLoop(connection, userName);
            }catch (IOException | ClassNotFoundException e){
                ConsoleHelper.writeMessage("IOException | ClassNotFoundException");
            }finally {
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));

                try {
                    if(connection != null) connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void sendBroadcastMessage(Message message){
        for(Map.Entry<String, Connection> entry : connectionMap.entrySet()){
            try {
                entry.getValue().send(message);
            } catch (IOException e) {
                //e.printStackTrace();
                ConsoleHelper.writeMessage("не удалось доставить сообщение пользователю " + entry.getKey());
            }
        }
    }
}
