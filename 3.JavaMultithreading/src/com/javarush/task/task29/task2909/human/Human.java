package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Human implements Alive {
    private static int nextId = 0;
    private int id;
    protected int age;
    protected String name;
    //protected int course;

    private List<Human> children = new ArrayList<>();

    //protected int[] size;
    protected Size size;

    //protected boolean isSoldier;

    /*public static final int FIRST = 1;
    public static final int SECOND = 2;
    public static final int THIRD = 3;
    public static final int FOURTH = 4;
    private int bloodGroup;*/
    private BloodGroup bloodGroup;

    /*public void setBloodGroup(int code) {
        bloodGroup = new BloodGroup(code);
    }*/
    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    /*public int getBloodGroup() {
        return bloodGroup.getCode();
    }*/

    /*public Human(boolean isSoldier) {
        this.isSoldier = isSoldier;
        this.id = nextId;
        nextId++;
    }*/

    public Human(String name, int age) {
        this.age = age;
        this.name = name;
        this.id = nextId;
        nextId++;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Human> getChildren() {
        //return children;
        return Collections.unmodifiableList(children);
    }

    /*public void setChildren(List<Human> children) {
        this.children = children;
    }*/

    public void addChild(Human human) {
        children.add(human);
    }

    public void removeChild(Human human) {
        if(children.contains(human)) children.remove(human);
    }

    /*public int getCourse() {
        return course;
    }*/

    public void live() {
        //if (isSoldier)
        //    fight();
    }

    //public void fight() {
    //}

    public int getId() {
        return id;
    }

    /*public void setId(int id) {
        this.id = id;
    }*/

    public void printSize() {
        System.out.println("Рост: " + size.height + " Вес: " + size.weight);
    }

    public String getPosition(){
        return "Человек";
    }

    public void printData() {
        System.out.println(getPosition() + ": " + name);
    }




    public class Size{
        public int height;
        public int weight;
    }


}