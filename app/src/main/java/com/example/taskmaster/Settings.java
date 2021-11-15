package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button submitUser=findViewById(R.id.submitUsername);
        submitUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userNameInput=findViewById(R.id.username);
                 String userName=userNameInput.getText().toString();
                SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(Settings.this);
                sharedPreferences.edit().putString("username",userName).apply();

            }
        });


    }
}