package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.adapter.StudentAdapter;
import com.example.myapplication.dao.StudentImpl;
import com.example.myapplication.model.Student;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mUiRecycler;
    private Button mUiBtnAdd;
    private ArrayList<Student> listStudent;
    private StudentAdapter adapter;
    private StudentImpl studentImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUiRecycler = findViewById(R.id.uiRecycler);
        mUiBtnAdd = findViewById(R.id.uiBtnAdd);
        studentImpl = new StudentImpl(MainActivity.this);
        listStudent = studentImpl.getAllStudent();
//        fakeData();
        adapter = new StudentAdapter(listStudent, this);
        mUiRecycler.setLayoutManager(new LinearLayoutManager(this));
        mUiRecycler.setAdapter(adapter);

        mUiBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormStudentActivity.class);
                intent.putExtra("type", Constant.ADD_STUDENT_REQUEST);
                startActivityForResult(intent, Constant.ADD_STUDENT_REQUEST);
            }
        });
    }

    private void fakeData() {
        listStudent =new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = new Student(i, "Le Van " + i, 23 + i, "Mob");
            listStudent.add(student);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==  Constant.ADD_STUDENT_REQUEST){
            if(resultCode == Activity.RESULT_OK){
                assert data != null;
                studentImpl = new StudentImpl(this);
                Student student = studentImpl.getAllStudent().get(studentImpl.getAllStudent().size()-1);
                listStudent.add(student);
                adapter.notifyDataSetChanged();
            }
        }else if( requestCode == Constant.VIEW_STUDENT_REQUEST){
            if (resultCode == Activity.RESULT_OK){
                int index = data.getIntExtra("index", -1);
                Student student = (Student) data.getSerializableExtra("student");
                listStudent.get(index).setName(student.getName());
                listStudent.get(index).setRoomClass(student.getRoomClass());
                listStudent.get(index).setAge(student.getAge());
                adapter.notifyDataSetChanged();
            }
        }
    }
}
