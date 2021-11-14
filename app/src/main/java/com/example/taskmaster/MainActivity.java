package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         ////
        Button nextPage1=findViewById(R.id.next1);
        nextPage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Navigate to Add task",Toast.LENGTH_LONG).show();
                // navigate to Addtask
                Intent goToAddtask=new Intent(MainActivity.this,AddTask.class);
                startActivity(goToAddtask);
            }
        });
        ////All Tasks
        Button allTasks=findViewById(R.id.button2);
        allTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Navigate to All tasks",Toast.LENGTH_LONG).show();
                //navigate to All tasks
                Intent goToAddtask=new Intent(MainActivity.this,AllTasks.class);
                startActivity(goToAddtask);

            }
        });


    }
}