package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.core.Amplify;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    AppDatabase taskDatabase;
    List<TaskModel> tasktoViewd=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        taskDatabase=AppDatabase.getInstance(this);
//        tasktoViewd = taskDatabase.userDao().getAll();

//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//
//
//            }
//        });

        // aws amplifier
        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }

        RecyclerView recyclerView = findViewById(R.id.allTasksView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(new TaskAdapter(tasktoViewd));





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









        Button settingPage=findViewById(R.id.setting);
        settingPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoSetting=new Intent(MainActivity.this,Settings.class);
                startActivity(gotoSetting);
            }
        });






    }


    ///////////////Test///////////
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart Called", Toast.LENGTH_LONG).show();
    }

    // Called when the activity has become visible.
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "onResume Called", Toast.LENGTH_LONG).show();
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userName = sharedPreferences.getString("username","the user didn't add a name yet!");
        TextView userNametext=findViewById(R.id.textUser);
        userNametext.setText(userName);

    }

    // Called when another activity is taking focus.
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause Called", Toast.LENGTH_LONG).show();
    }

    // Called when the activity is no longer visible.
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop Called", Toast.LENGTH_LONG).show();
    }


    //It is invoked after the activity has been stopped and prior to its starting stage.
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "onRestart Called", Toast.LENGTH_LONG).show();
    }

    // Called just before the activity is destroyed.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy Called", Toast.LENGTH_LONG).show();
    }





}