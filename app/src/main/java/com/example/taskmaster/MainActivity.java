package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Task> allTasks = new ArrayList<Task>();
    TaskRoomDatabase taskRoomDatabase;
    private TaskAdapter.RecyclerViewClickListener recyclerViewClickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskRoomDatabase =  TaskRoomDatabase.getData(this);

//        allTasks.add(new Task("math","solve it","new"));
//        allTasks.add(new Task("english","solved","complete"));
//        allTasks.add(new Task("arabic","fix it","in progress"));


        Button goToActivityTwo = findViewById(R.id.addTaskBtn);
        Button goToActivityThree = findViewById(R.id.allTasksBtn);
//        Button task1 = findViewById(R.id.newTitle);
//        Button task2 = findViewById(R.id.task2btn);
//        Button task3 = findViewById(R.id.task3btn);
        Button settingPage = findViewById(R.id.settingbtn);

//        setOnClickListener();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                allTasks = taskRoomDatabase.taskDao().getAll();
                System.out.println(allTasks);
                RecyclerView recyclerView = findViewById(R.id.recycleId);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(new TaskAdapter(allTasks,recyclerViewClickListener));
            }
        });




        goToActivityTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToActivityTwoIntent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(goToActivityTwoIntent);
            }
        });

        goToActivityThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToActivityThreeIntent = new Intent(MainActivity.this,MainActivity3.class);
                startActivity(goToActivityThreeIntent);
            }
        });

//        task1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent taskOneIntent = new Intent(MainActivity.this, MainActivity4.class);
//                taskOneIntent.putExtra("title", "Task1");
//                startActivity(taskOneIntent);
//            }
//        });
//
//        task2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent taskTwoIntent = new Intent(MainActivity.this, MainActivity4.class);
//                taskTwoIntent.putExtra("title", "Task2");
//                startActivity(taskTwoIntent);
//            }
//        });
//
//        task3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent taskThreeIntent = new Intent(MainActivity.this, MainActivity4.class);
//                taskThreeIntent.putExtra("title", "Task3");
//                startActivity(taskThreeIntent);
//            }
//        });

        settingPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSettingPage = new Intent(MainActivity.this, MainActivity5.class);
                startActivity(goToSettingPage);
            }
        });

    }

    private void setOnClickListener() {
        recyclerViewClickListener = new TaskAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(),MainActivity4.class);
                intent.putExtra("title",allTasks.get(position).getTitle());
//                intent.putExtra("body",allTasks.get(position).getBody());
//                intent.putExtra("state",allTasks.get(position).getState());

                startActivity(intent);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String name = sharedPreferences.getString("usernameInput", "Balqees");
        TextView textView = findViewById(R.id.userInput);
        textView.setText(name);



    }
}