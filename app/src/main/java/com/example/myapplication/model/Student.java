package com.example.myapplication.model;

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String name;
    private int age;
    private String roomClass;
    
    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", roomClass='" + roomClass + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }

    public Student(String name, int age, String roomClass) {
        this.name = name;
        this.age = age;
        this.roomClass = roomClass;
    }

    public Student(int id, String name, int age, String roomClass) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.roomClass = roomClass;
    }
}
