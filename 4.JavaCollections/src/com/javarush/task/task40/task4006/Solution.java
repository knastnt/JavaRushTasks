package com.javarush.task.task40.task4006;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

/* 
Отправка GET-запроса через сокет
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(URL url) {
        try {
            Socket socket = new Socket(url.getHost(), 80);

            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();

            os.write(("GET " + url.getFile() + " HTTP/1.1\r\n" +
                    "Host: " + url.getHost() + "\r\n" +
                    "Accept: text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2\r\n" +
                    "User-Agent: Java/1.8.0_181\r\n" +
                    "Connection: close\r\n\r\n").getBytes());
            os.flush();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String responseLine;

            while ((responseLine = bufferedReader.readLine()) != null) {
                System.out.println(responseLine);
            }
            bufferedReader.close();

            is.close();
            os.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}