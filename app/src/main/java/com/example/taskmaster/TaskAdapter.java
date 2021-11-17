package com.example.taskmaster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    public TaskAdapter(MainActivity mainActivity, List<TaskModel> allTasks) {
        this.allTasks = allTasks;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewholder holder, int position) {
        holder.taskModel=allTasks.get(position);

        TextView taskTitle=holder.itemVIew.findViewById(R.id.titleTextView);
        TextView taskBody=holder.itemVIew.findViewById(R.id.bodyTextView);
        TextView taskState=holder.itemVIew.findViewById(R.id.stateTextView);

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
