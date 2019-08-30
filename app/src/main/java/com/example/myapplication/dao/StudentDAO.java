package com.example.myapplication.dao;

import com.example.myapplication.model.Student;

import java.util.ArrayList;

public interface StudentDAO {
    ArrayList<Student> getAllStudent();

    Student getStudent(int id);

    boolean updateStudent(Student student);

    boolean deleteStudent(int id);

    boolean insertStudent(Student student);
}
