package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;

import java.util.List;

public class AddTask extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //public AppDatabase taskDatabase;
    String string="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        //   taskDatabase=AppDatabase.getInstance(this);
        EditText taskTitle=findViewById(R.id.taskTilte);
        EditText taskDecrep=findViewById(R.id.descreption);
        EditText taskState=findViewById(R.id.state);
        Button addTask=findViewById(R.id.submitAdd);

        //Spinner
        Spinner newSpiner=findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.hello,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        newSpiner.setAdapter(adapter);
        newSpiner.setOnItemSelectedListener(this);



        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String myTitel1 = taskTitle.getText().toString();
                String taskDcrep = taskDecrep.getText().toString();
                String stateTas = taskState.getText().toString();
//                UserDao userDao = taskDatabase.userDao();
//                userDao.insert(new TaskModel(myTitel1,taskDcrep,stateTas));

                Toast.makeText(getApplicationContext(), "hi from add", Toast.LENGTH_LONG).show();

                Task taskModel = Task.builder()
                        .title(myTitel1)
                        .body(taskDcrep)
                        .state(stateTas)
                        .build();


                Amplify.API.mutate(
                        ModelMutation.create(taskModel),
                        response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                        error -> Log.e("MyAmplifyApp", "Create failed", error)
                );


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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
         string=adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(), string, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}