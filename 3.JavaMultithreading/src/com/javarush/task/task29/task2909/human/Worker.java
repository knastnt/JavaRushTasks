package com.javarush.task.task29.task2909.human;

public class Worker extends Human {
    //private Human human;
    private double salary;
    private String company;

    public Worker(String name, int age) {
        super(name, age);
    }

    /*public Worker(String name, int age) {
        human = new Human(name, age);
    }*/

    /*public void live() {
        human.live();
    }*/

    public double getSalary() {
        return salary;
    }

    /*public void setSlr(double salary) {
        this.salary = salary;
    }*/

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}