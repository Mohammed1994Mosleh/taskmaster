package com.example.taskmaster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewholder> {

    List<TaskModel> allTasks=new ArrayList<TaskModel>();

    @NonNull
    @Override
    public TaskViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task,parent,false);
        TaskViewholder taskViewholder=new TaskViewholder(view);
        return taskViewholder;
    }

    public TaskAdapter( List<TaskModel> allTasks) {
        this.allTasks = allTasks;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewholder holder, int position) {
        holder.taskModel=allTasks.get(position);

        TextView taskTitle = holder.itemVIew.findViewById(R.id.frgamentTiltle);
        TextView taskBody=holder.itemVIew.findViewById(R.id.body);
        TextView taskState=holder.itemVIew.findViewById(R.id.status);

        Button showTask=holder.itemVIew.findViewById(R.id.showtask);
        showTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taskTitle=holder.taskModel.getTitle();
                Intent goToDetailsPage=new Intent(view.getContext(),Taskdetail.class);
                goToDetailsPage.putExtra("taskTitle",taskTitle);
                view.getContext().startActivity(goToDetailsPage);
            }
        });

        taskTitle.setText(holder.taskModel.title);
        taskBody.setText(holder.taskModel.body);
        taskState.setText(holder.taskModel.state);


      //  studentName.setText(holder.taskModel.);

    }

    @Override
    public int getItemCount() {
        return allTasks.size();
    }

    public static class TaskViewholder extends RecyclerView.ViewHolder{
        public TaskModel taskModel;
        public View itemVIew;


        public TaskViewholder(@NonNull View itemView) {
            super(itemView);
            this.itemVIew=itemView;
        }
    }
}
