package com.example.myapplication.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Constant;
import com.example.myapplication.FormStudentActivity;
import com.example.myapplication.R;
import com.example.myapplication.dao.StudentImpl;
import com.example.myapplication.model.Student;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private ArrayList<Student> listStudent;
    private Context context;
    private StudentImpl studentImpl;
    public StudentAdapter(ArrayList<Student> listStudent, Context context) {
        this.listStudent = listStudent;
        this.context = context;
        this.studentImpl = new StudentImpl(context);
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final StudentViewHolder holder, int position) {
        holder.mItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FormStudentActivity.class);
                intent.putExtra("student", listStudent.get(holder.getAdapterPosition()));
                intent.putExtra("type", Constant.VIEW_STUDENT_REQUEST);
                intent.putExtra("index", holder.getAdapterPosition());
                ((Activity) context).startActivityForResult(intent, Constant.VIEW_STUDENT_REQUEST);
            }
        });
        holder.mItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do u want delete this student?");
                builder.setTitle("Alert");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        boolean check = studentImpl.deleteStudent(listStudent.get(holder.getAdapterPosition()).getId());
                        Log.e("123",check+"");
                        if(check){
                            listStudent.remove(holder.getAdapterPosition());
                            notifyItemRemoved(holder.getAdapterPosition());
                        }
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
                return true;
            }
        });
        int index = holder.getAdapterPosition();
        holder.mTvIndex.setText(index+1+"");
        holder.mTvName.setText(listStudent.get(index).getName());
        holder.mTvAge.setText(listStudent.get(index).getAge()+"");
        holder.mTvRoomClass.setText(listStudent.get(index).getRoomClass());
    }

    @Override
    public int getItemCount() {
        return listStudent.size();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder {

        LinearLayout mItem;
        TextView mTvIndex;
        TextView mTvName;
        TextView mTvAge;
        TextView mTvRoomClass;

        private StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            mItem = itemView.findViewById(R.id.item);
            mTvIndex = itemView.findViewById(R.id.tvIndex);
            mTvName = itemView.findViewById(R.id.tvName);
            mTvAge = itemView.findViewById(R.id.tvAge);
            mTvRoomClass = itemView.findViewById(R.id.tvRoomClass);

        }
    }
}
