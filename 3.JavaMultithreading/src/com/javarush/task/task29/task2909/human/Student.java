package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student extends UniversityPerson {
    //private List<Human> children = new ArrayList<>();
    private double averageGrade;
    //private String university;
    private Date beginningOfSession;
    private Date endOfSession;
    private int course;

    /*public Student(String name, int age, double averageGrade) {
        super(name, age);
        this.averageGrade = averageGrade;
    }*/

    public Student(String name, int age, double averageGrade) {
        super(name, age);
        this.averageGrade = averageGrade;
    }
    /*public List<Human> getChildren() {
        return children;
    }

    public void setChildren(List<Human> children) {
        this.children = children;
    }*/

    public int getCourse() {
        return course;
    }

    public void live() {
        learn();
    }

    public void learn() {
    }

    /*public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }*/

    /*public void printData() {
        System.out.println("Студент: " + name);
    }*/

    /*public void incAverageGradeBy01() {
        averageGrade += 0.1;
    }

    public void incAverageGradeBy02() {
        averageGrade += 0.2;
    }*/

    public void incAverageGrade(double delta) {
        //averageGrade += delta;
        setAverageGrade(getAverageGrade() + delta);
    }

    /*public void setValue(String name, double value) {
        if (name.equals("averageGrade")) {
            averageGrade = value;
            return;
        }
        if (name.equals("course")) {
            course = (int) value;
            return;
        }
    }*/

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    /*public void setBeginningOfSession(int day, int month, int year) {
        beginningOfSession = new Date(year, month, day);
    }

    public void setEndOfSession(int day, int month, int year) {
        endOfSession = new Date(year, month, day);
    }*/

    public void setBeginningOfSession(Date date) {
        beginningOfSession = date;
    }

    public void setEndOfSession(Date date) {
        endOfSession = date;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    @Override
    public String getPosition() {
        return "Студент";
    }
}