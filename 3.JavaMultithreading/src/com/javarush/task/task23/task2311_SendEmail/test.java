package com.javarush.task.task23.task2311_SendEmail;



import javax.mail.*;
import javax.mail.internet.*;

import java.util.Properties;

public class test {

    public static void main(String[] args) {

        //Отправить E-Mail

        //Скачать javamail api (javax.mail.jar) отсюда http://www.oracle.com/technetwork/java/javamail/index.html
        //В Intellij IDEA в меню File->Project Structure...->Libraries нажать плюсик и добавить этот файл к проекту

        //Тто же самое сделать для JAF (activation.jar): http://www.oracle.com/technetwork/java/javase/jaf-136260.html


        final String username = "address@mail.ru";
        final String password = "password";

        final String target = "target@mail.ru";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.mail.ru");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(target));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler,"
                    + "\n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
