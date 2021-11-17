package com.example.taskmaster;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {
   private TaskRoomDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        EditText editText1 = findViewById(R.id.editTitleId);
        EditText editText2 = findViewById(R.id.desId);

        db =  TaskRoomDatabase.taskRoomInstance;
//        TaskRoomDatabase db = Room.databaseBuilder(getApplicationContext(),TaskRoomDatabase.class,"task").allowMainThreadQueries().fallbackToDestructiveMigration().build();






        Button submitted = findViewById(R.id.button3);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Taskmaster");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        submitted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                TaskDao taskDao = db.taskDao();
//                taskDao.insert(new Task("myTitle","muyBody","myState"));
//                List<Task> tasks = taskDao.getAll().getValue();
//                System.out.println(tasks);

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        String myTitel1 = editText1.getText().toString();
                        String myTitel2 = editText2.getText().toString();
                        TaskDao taskDao = db.taskDao();
                    taskDao.insert(new Task(myTitel1,myTitel2,"new"));
                    List<Task> tasks = taskDao.getAll();
                    System.out.println(tasks);
                    }
                });
                Toast.makeText(getApplicationContext(),"Submittet",Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity2.this,MainActivity.class));
            }
        });

    }


}