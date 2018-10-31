package com.javarush.task.task30.task3008;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Класс - обёртка вокруг класса Socket
 *
 * Есть два стрима для объектов - входящий и исходящий.
 * Умеет отправлять объекты класса Message в стрим и извлекать их оттуда
 */
public class Connection implements Closeable {
    private final Socket socket;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    public void send(Message message) throws IOException{
        synchronized (out){
            out.writeObject(message);
        }
    }

    public Message receive() throws IOException, ClassNotFoundException{
        synchronized (in){
            //Этот метод внутри использует задержку и продолжает выполнение когда обнаружится новый переданный объект
            Message message = (Message) in.readObject();

            //System.out.println(message);
            return message;
        }
    }

    public SocketAddress getRemoteSocketAddress(){
        return socket.getRemoteSocketAddress();
    }

    public void close() throws IOException{
        in.close();
        out.close();
        socket.close();
    }
}
