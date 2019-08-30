package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.dao.StudentImpl;
import com.example.myapplication.model.Student;

public class FormStudentActivity extends AppCompatActivity {
    private EditText mEdtName;
    private EditText mEdtAge;
    private EditText mEdtClass;
    private Button mBtnSubmit;
    private Button mBtnCancel;
    private Intent intent;
    private int type;
    private StudentImpl studentImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_student);
        intent = getIntent();
        type = intent.getIntExtra("type", 0);
        mEdtName = findViewById(R.id.edtName);
        mEdtAge = findViewById(R.id.edtAge);
        mEdtClass = findViewById(R.id.edtClass);
        mBtnSubmit = findViewById(R.id.btnSubmit);
        mBtnCancel = findViewById(R.id.btnCancel);
        studentImpl = new StudentImpl(FormStudentActivity.this);
        if (type == Constant.VIEW_STUDENT_REQUEST){
            Student student = (Student) intent.getSerializableExtra("student");
            mEdtName.setText(student.getName());
            mEdtAge.setText(student.getAge()+"");
            mEdtClass.setText(student.getRoomClass());
        }
        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mEdtName.getText().toString();
                int age;
                String roomClass;
                if (!name.isEmpty()) {
                    age = Integer.parseInt(mEdtAge.getText().toString());
                    roomClass = mEdtClass.getText().toString();
                    if (type == Constant.ADD_STUDENT_REQUEST) {
                        Student student = new Student(name, age, roomClass);
                        Intent intent = new Intent();
                        intent.putExtra("student", student);
                        boolean check = studentImpl.insertStudent(student);
                        if(check){
                            setResult(Activity.RESULT_OK, intent);
                            finish();
                        }else {
                            Toast.makeText(FormStudentActivity.this, "Insert student failed", Toast.LENGTH_SHORT).show();
                        }
                    }else if (type == Constant.VIEW_STUDENT_REQUEST){
                        Student student = new Student();
                        student.setId(((Student) intent.getSerializableExtra("student")).getId());
                        student.setName(name);
                        student.setAge(age);
                        student.setRoomClass(roomClass);
                        boolean check = studentImpl.updateStudent(student);
                        Intent intent2 = new Intent();
                        intent2.putExtra("index", intent.getIntExtra("index", -1));
                        intent2.putExtra("student", student);
                        Log.e("123", check+"");
                        if (check){
                            setResult(Activity.RESULT_OK, intent2);
                            finish();
                        }
                    }
                }
            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
