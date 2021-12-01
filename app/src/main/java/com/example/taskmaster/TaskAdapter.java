package com.example.taskmaster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

import com.amplifyframework.datastore.generated.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewholder> {

    List<Task> allTasks=new ArrayList<Task>();

    @NonNull
    @Override
    public TaskViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task,parent,false);
        TaskViewholder taskViewholder=new TaskViewholder(view);
        return taskViewholder;
    }

    public TaskAdapter( List<Task> allTasks) {
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
                String taskDes=holder.taskModel.getBody();
                String taskStatus=holder.taskModel.getState();
                String image= holder.taskModel.getFileKey();
                Intent goToDetailsPage=new Intent(view.getContext(),Taskdetail.class);
                goToDetailsPage.putExtra("taskTitle",taskTitle);
                goToDetailsPage.putExtra("taskDes",taskDes);
                goToDetailsPage.putExtra("taskImage",image);
                goToDetailsPage.putExtra("taskStatus",taskStatus);

                view.getContext().startActivity(goToDetailsPage);
            }
        });

        taskTitle.setText(holder.taskModel.getTitle());
        taskBody.setText(holder.taskModel.getBody());
        taskState.setText(holder.taskModel.getState());


      //  studentName.setText(holder.taskModel.);

    }

    @Override
    public int getItemCount() {
        return allTasks.size();
    }

    public static class TaskViewholder extends RecyclerView.ViewHolder{
        public Task taskModel;
        public View itemVIew;


        public TaskViewholder(@NonNull View itemView) {
            super(itemView);
            this.itemVIew=itemView;
        }
    }
}
