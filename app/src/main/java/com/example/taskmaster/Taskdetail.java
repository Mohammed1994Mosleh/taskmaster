package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Taskdetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskdetail);

        TextView textView = (TextView)findViewById(R.id.lorem);
        String desc=getIntent().getExtras().get("taskDes").toString();
        textView.setText(desc);


        TextView title=findViewById(R.id.taskdetail);
        String titleFromhome = getIntent().getExtras().get("taskTitle").toString();
        title.setText(titleFromhome);

        TextView state=findViewById(R.id.detailstate);
        String stateDetail = getIntent().getExtras().get("taskStatus").toString();
        state.setText(stateDetail);


    }
}