package com.javarush.task.task23.task2311_;

/*import java.util.Arrays;
import java.util.Comparator;*/

import java.util.Properties;

public class test {
    /*static class MyComparator implements Comparator<String> {
        public int compare(String strA, String strB) {
            return strB.compareTo(strA);
        }
    }*/

    public static void main(String[] args) {
        //Сортировать массив строк в обратном порядке

        /*String[] mass = new String[]{"hello", "world", "any_shit", "my_word"};
        MyComparator rsc = new MyComparator();
        Arrays.sort(mass, rsc);
        System.out.println(Arrays.toString(mass));*/

        //Отправить E-Mail

        //Скачать javamail api отсюда http://www.oracle.com/technetwork/java/javamail/index.html

        // Recipient's email ID needs to be mentioned.
        String to = "knastnt@yandex.ru";

        // Sender's email ID needs to be mentioned
        String from = "456kot@mail.ru";

        // Assuming you are sending email from localhost
        String host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("smtp.mail.ru", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            messageBodyPart.setText("This is message body");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = "file.txt";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart );

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
