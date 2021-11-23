package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class AddTask extends AppCompatActivity {

    public AppDatabase taskDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        taskDatabase=AppDatabase.getInstance(this);
        EditText taskTitle=findViewById(R.id.taskTilte);
        EditText taskDecrep=findViewById(R.id.descreption);
        EditText taskState=findViewById(R.id.satateTask);
        Button addTask=findViewById(R.id.submitAdd);

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String myTitel1 = taskTitle.getText().toString();
                String taskDcrep = taskDecrep.getText().toString();
                String stateTas = taskState.getText().toString();
                UserDao userDao = taskDatabase.userDao();
                userDao.insert(new TaskModel(myTitel1,taskDcrep,stateTas));

                Toast.makeText(getApplicationContext(), "hi from add", Toast.LENGTH_LONG).show();


                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                      //  Toast.makeText(getApplicationContext(), "hiiiiiii", Toast.LENGTH_LONG).show();

                    }
                });

//                TaskModel newTask=new TaskModel(taskTitle, taskDecrep, taskState);
//                 appDatabase=AppDatabase.appDatabase;
//                 UserDao userDao=appDatabase.userDao();
//                 userDao.insert(newTask);
//                List <TaskModel> tasks=userDao.getAll();
//                System.out.println(tasks);

            }
        });



    }
}