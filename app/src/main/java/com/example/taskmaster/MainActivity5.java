package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        Button save  = findViewById(R.id.savebtn);
        Button home = findViewById(R.id.homebtn);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToHomeIntent = new Intent(MainActivity5.this, MainActivity.class);
                startActivity(goToHomeIntent);

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText username = findViewById(R.id.editTextUsername);
                String usernameInput =  username.getText().toString();

                SharedPreferences  sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity5.this);
                SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
                sharedPreferencesEditor.putString("usernameInput",usernameInput);
                sharedPreferencesEditor.apply();

            }
        });
    }
}