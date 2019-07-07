package com.example.onlineapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.Holder> {

    private List<Student> studentlist;
    private Context context;

    public StudentAdapter(List<Student> studentlist, Context context) {
        this.studentlist = studentlist;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_student,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {

        holder.studentid.setText(studentlist.get(position).getStudentid());
        holder.name.setText(studentlist.get(position).getName());
        holder.email.setText(studentlist.get(position).getEmail());
        holder.phone.setText(studentlist.get(position).getPhoneno());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = studentlist.get(position).getId();
                String studentid = studentlist.get(position).getStudentid();
                String name = studentlist.get(position).getName();
                String email = studentlist.get(position).getEmail();
                String phone = studentlist.get(position).getPhoneno();


                Intent a = new Intent(context, StudentDetailsActivity.class);
                a.putExtra("id",id);
                a.putExtra("student_id",studentid);
                a.putExtra("name",name);
                a.putExtra("email",email);
                a.putExtra("phone",phone);
                context.startActivity(a);

            }
        });


    }

    @Override
    public int getItemCount() {
        return studentlist.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView studentid,name,email,phone;

        public Holder(@NonNull View itemView) {
            super(itemView);
            studentid = itemView.findViewById(R.id.studentid);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            phone = itemView.findViewById(R.id.phone);

        }
    }
}
