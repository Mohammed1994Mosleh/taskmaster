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
        textView.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                " Maecenas tincidunt consectetur odio, vitae feugiat sapien" +
                " fermentum at. Integer bibendum ultricies massa, in facilisis " +
                "sapien dictum sed. Donec ullamcorper lectus mattis diam efficitur" +
                " tempus. Duis consectetur, felis vitae facilisis commodo, libero nunc " +
                "semper turpis, vel euismod felis tortor nec tellus");


        TextView title=findViewById(R.id.taskdetail);
        String titleFromhome = getIntent().getExtras().get("title1").toString();
        title.setText(titleFromhome);
    }
}