package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Student> students = new ArrayList<>();
    private String name;
    private int age;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /*public University(String name, int age) {
        super(name, age, 0);
    }*/

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double ball) {
        //TODO:
        for (Student student : students) {
            if(student.getAverageGrade() >= ball) return student;
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        Student returnedStudent = null;
        double ball = 0;
        for (Student student : students) {
            if(student.getAverageGrade() > ball){
                ball = student.getAverageGrade();
                returnedStudent = student;
            }
        }
        return returnedStudent;
    }

    public Student getStudentWithMinAverageGrade() {
        //TODO:
        Student returnedStudent = null;
        Double ball = null;
        for (Student student : students) {
            if(ball == null || student.getAverageGrade() < ball){
                ball = student.getAverageGrade();
                returnedStudent = student;
            }
        }
        return returnedStudent;
    }

    public void expel(Student student) {
        //TODO:
        if(students.contains(student)){
            students.remove(student);
        }
    }
}