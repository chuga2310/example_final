package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.database.StudentDatabase;
import com.example.myapplication.model.Student;

import java.util.ArrayList;

public class StudentImpl implements StudentDAO {
    private StudentDatabase db;

    public StudentImpl(Context context) {
        this.db = new StudentDatabase(context);
    }

    @Override
    public ArrayList<Student> getAllStudent() {
        SQLiteDatabase sqlDB = db.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery("SELECT * FROM STUDENT", null);
        ArrayList<Student> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int age = cursor.getInt(cursor.getColumnIndex("age"));
                String roomClass = cursor.getString(cursor.getColumnIndex("class"));
                Student student = new Student(id, name, age, roomClass);
                list.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return list;
    }

    @Override
    public Student getStudent(int id) {
        SQLiteDatabase sqlDB = db.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery("SELECT * FROM STUDENT WHERE id = ?", new String[]{String.valueOf(id)});
        Student student = new Student();
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int age = cursor.getInt(cursor.getColumnIndex("age"));
                String roomClass = cursor.getString(cursor.getColumnIndex("class"));
                student.setId(id);
                student.setName(name);
                student.setAge(age);
                student.setRoomClass(roomClass);
            } while (cursor.moveToNext());
        }
        cursor.close();
        sqlDB.close();
        return student;
    }

    @Override
    public boolean updateStudent(Student student) {
        SQLiteDatabase sqlDB = db.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", student.getId());
        values.put("name", student.getName());
        values.put("age", student.getAge());
        values.put("class", student.getRoomClass());
        int check = sqlDB.update("STUDENT", values, "id=?", new String[]{String.valueOf(student.getId())});
        sqlDB.close();
        return check != 0;
    }

    @Override
    public boolean deleteStudent(int id) {
        SQLiteDatabase sqlDB = db.getWritableDatabase();
        int check = sqlDB.delete("STUDENT", "id=?", new String[]{String.valueOf(id)});
        return check != 0;
    }

    @Override
    public boolean insertStudent(Student student) {
        SQLiteDatabase sqlDB = db.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", student.getName());
        values.put("age", student.getAge());
        values.put("class", student.getRoomClass());
        long check = sqlDB.insert("STUDENT", null, values);
        return check != -1;
    }
}
