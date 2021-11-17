package com.example.taskmaster;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    List<Task> allTasks = new ArrayList<Task>();
    private static  RecyclerViewClickListener recyclerViewClickListener;


    public TaskAdapter(List<Task> allTasks , RecyclerViewClickListener recyclerViewClickListener) {
        this.allTasks = allTasks;
        this.recyclerViewClickListener = recyclerViewClickListener;

    }

    public TaskAdapter(List<Task> allTasks) {
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_blank,parent,false);
        TaskViewHolder taskViewHolder = new TaskViewHolder(view);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.task = allTasks.get(position);


        TextView title = holder.itemView.findViewById(R.id.fragTitle);
        TextView body = holder.itemView.findViewById(R.id.fragBody);
        TextView state = holder.itemView.findViewById(R.id.fragStatus);

        title.setText(holder.task.getTitle());
        body.setText(holder.task.getBody());
        state.setText(holder.task.getState());

//db
        String titleToHandle = title.getText().toString();
        String descToHandle = body.getText().toString();
        String statetodaaaamn = state.getText().toString();

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),MainActivity4.class);
                i.putExtra("taskTitle", titleToHandle);
                i.putExtra("desc", descToHandle);
                i.putExtra("state", statetodaaaamn);

                v.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allTasks.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public  Task task;
        public View itemView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recyclerViewClickListener.onClick(view,getAdapterPosition());

        }
    }

    public interface RecyclerViewClickListener{
        void onClick(View v ,int position);
    }
}
