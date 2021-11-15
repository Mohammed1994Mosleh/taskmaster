package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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


        Button deatils1=findViewById(R.id.Title1);
        deatils1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddtask=new Intent(MainActivity.this,Taskdetail.class);
                goToAddtask.putExtra("title1","title1");
                startActivity(goToAddtask);

            }
        });

        Button deatils2=findViewById(R.id.Title2);
        deatils2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddtask=new Intent(MainActivity.this,Taskdetail.class);
                goToAddtask.putExtra("title1","title2");
                startActivity(goToAddtask);

            }
        });

        Button deatils3=findViewById(R.id.Title3);
        deatils3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddtask=new Intent(MainActivity.this,Taskdetail.class);
                goToAddtask.putExtra("title1","title3");
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