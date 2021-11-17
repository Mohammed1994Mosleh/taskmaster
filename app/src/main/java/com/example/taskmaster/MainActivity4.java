package com.example.taskmaster;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Taskmaster");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        String taskName = getIntent().getStringExtra("taskTitle");
        TextView tasktitle = findViewById(R.id.textView7);
        tasktitle.setText(taskName);


        String desc = getIntent().getStringExtra("desc");
        TextView descstuff = findViewById(R.id.decription);
        descstuff.setText(desc);

        String state = getIntent().getStringExtra("state");
        TextView statestuff = findViewById(R.id.statenewId);
        statestuff.setText(state);


//        String value1 = getIntent().getExtras().get("title").toString();
//        String value2 = getIntent().getExtras().get("title").toString();
//        String value3 = getIntent().getExtras().get("title").toString();
//
//        theTitle.setText(value1);
//        theTitle.setText(value2);
//        theTitle.setText(value3);

    }
}