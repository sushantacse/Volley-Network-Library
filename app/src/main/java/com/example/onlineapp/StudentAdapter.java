package com.example.onlineapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

        holder.studentid.setText(studentlist.get(position).getId());
        holder.name.setText(studentlist.get(position).getName());
        holder.email.setText(studentlist.get(position).getEmail());
        holder.phone.setText(studentlist.get(position).getPhoneno());

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
