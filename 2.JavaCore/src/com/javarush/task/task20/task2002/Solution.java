package com.javarush.task.task20.task2002;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут

            User user = new User();
            user.setBirthDate(new Date());
            user.setCountry(User.Country.RUSSIA);
            user.setFirstName("Vanya");
            user.setLastName("famil");
            user.setMale(true);

            javaRush.users.add(user);
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны

            System.out.println(javaRush.equals(loadedObject));

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            PrintStream printStream = new PrintStream(outputStream);
            printStream.println("start_javarush");
            printStream.println(users.size() != 0 ? users.size() : "0");
            for (User user : users) {
                printStream.println(user.getBirthDate().getTime());
                printStream.println(user.getCountry());
                printStream.println(user.getFirstName());
                printStream.println(user.getLastName());
                printStream.println(user.isMale());
            }
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader brdr= new BufferedReader(new InputStreamReader(inputStream));
            while (brdr.ready() && brdr.readLine().equals("start_javarush")){
                int count = Integer.parseInt(brdr.readLine());
                if(count>0){
                    for (int i = 0; i < count; i++) {
                        User user = new User();
                        user.setBirthDate(new Date(Long.parseLong(brdr.readLine())));
                        user.setCountry(User.Country.valueOf(brdr.readLine()));
                        user.setFirstName(brdr.readLine());
                        user.setLastName(brdr.readLine());
                        user.setMale(brdr.readLine().equals("true") ? true : false);

                        users.add(user);
                    }
                }
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
