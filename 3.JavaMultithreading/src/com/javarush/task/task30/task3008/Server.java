package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.javarush.task.task30.task3008.MessageType.*;

public class Server {
    //список всех установленных соединений
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        ConsoleHelper.writeMessage("Введите порт сервера:");
        int port = ConsoleHelper.readInt();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("сервер запущен");
            while (true) {
                //основной поток сервера крутится в бесконечном цикле, постоянно отлавливая
                // новые входящие соединения и создавая для каждого из них свой трэд

                //Этот метод внутри использует задержку и продолжает выполнение когда обнаружится новое подключение
                Socket socket = serverSocket.accept();

                //Создаёт новый трэд лля этого соединения
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

    /**
     * На каждое соединение с клиентом - по объекту этого класса
     *
     * это отдельный ТРЭД
     */
    private static class Handler extends Thread{
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        //метод рукопожатия. получает имя пользователя и регистрирует его в списке подключений
        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException{
            connection.send(new Message(NAME_REQUEST));
            while (true){
                //Этот метод внутри использует задержку и продолжает выполнение когда обнаружится новое сообщение
                Message message = connection.receive();

                //Если тип полученного в ответ сообщения не равен USER_NAME, то шлём запрос снова
                if(message.getType() != USER_NAME){
                    connection.send(new Message(NAME_REQUEST));
                    continue;
                }

                //Сообщение нужного типа
                //Проверяем не пусты ли текстовые данные
                //И нет ли уже такого имени в сиске подключенных
                if(message.getData() != null && !message.getData().equals("") && !connectionMap.containsKey(message.getData())){
                    //Добавляем это соединение в список
                    connectionMap.put(message.getData(), connection);
                    //Говорим клиенту что он добавлен
                    connection.send(new Message(NAME_ACCEPTED));
                    //Возвращаем имя
                    return message.getData();
                }else{
                    //Перезапрашиваем имя если условия не выполнились
                    connection.send(new Message(NAME_REQUEST));
                    continue;
                }

            }
        }

        //Отсылаем список всех участноков чата (кроме его самого - userName)
        private void sendListOfUsers(Connection connection, String userName) throws IOException{
            for(Map.Entry<String, Connection> entry : connectionMap.entrySet()){
                if(!entry.getKey().equals(userName)){
                    connection.send(new Message(USER_ADDED, entry.getKey()));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException{
            while (true){
                //Ждём прихода сообщения
                Message message = connection.receive();

                //Сообщение пришло
                //Проверяем его тип
                if(message.getType() == MessageType.TEXT){
                    //Перенаправляем это сообщение всем
                    String string = userName + ": " + message.getData();
                    sendBroadcastMessage(new Message(MessageType.TEXT,string));
                }else{
                    ConsoleHelper.writeMessage("The message no TEXT");
                }
            }
        }

        @Override
        public void run() {
            //Выполняется в отдельном трэде при подключении нового клиента
            ConsoleHelper.writeMessage("Connected with " + socket.getRemoteSocketAddress());
            Connection connection = null;
            String userName = "";
            try {
                //Создаём коннекшн для пересылки сообщений
                connection = new Connection(socket);
                //Делаем рукопожатие (добавляем в список соединений) и получаем имя
                userName = serverHandshake(connection);
                //Сообщаем всем это этот человек подключился
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                //Посылаем этому пользователю список всех участников чата
                sendListOfUsers(connection, userName);
                //Переходим в стадию обслуживания (бесконечный цикл)
                serverMainLoop(connection, userName);
            }catch (IOException | ClassNotFoundException e){
                ConsoleHelper.writeMessage("IOException | ClassNotFoundException");
            }finally {
                //убираем соединение из списка в случае ошибки
                connectionMap.remove(userName);
                //Говорим всем что пользователь ушел
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));

                try {
                    if(connection != null) connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // метод для отправки бродкастовых сообщений
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
