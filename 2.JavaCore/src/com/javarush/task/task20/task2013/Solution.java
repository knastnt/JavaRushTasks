package com.javarush.task.task20.task2013;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Externalizable Person
*/
public class Solution {
    public static class Person implements Externalizable {
        private String firstName;
        private String lastName;
        private int age;
        private Person mother;
        private Person father;
        private List<Person> children;

        public Person() {
        }

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public void setMother(Person mother) {
            this.mother = mother;
        }

        public void setFather(Person father) {
            this.father = father;
        }

        public void setChildren(List<Person> children) {
            this.children = children;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(mother);
            out.writeObject(father);
            out.writeObject(firstName);
            out.writeObject(lastName);
            out.writeInt(age);
            out.writeObject(children);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

            mother = (Person)in.readObject();
            father = (Person)in.readObject();
            firstName = (String)in.readObject();
            lastName = (String)in.readObject();
            age = in.readInt();
            children = (List)in.readObject();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person person=new Person("gena", "pupkin", 28);
        Person fperson=new Person("vanya", "pupkin", 58);
        Person mperson=new Person("alena", "pupkina", 55);
        person.setFather(fperson);
        person.setMother(mperson);
        Person ch1 = new Person("leva", "pupkin", 5);
        Person ch2 = new Person("katya", "pupkina", 3);
        List<Person> lp = new ArrayList<>();
        lp.add(ch1);
        lp.add(ch2);
        person.setChildren(lp);


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
        oos.writeObject(person);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        Person person2 = (Person)ois.readObject();
        ois.close();
    }
}
