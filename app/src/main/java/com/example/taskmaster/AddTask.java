package com.example.taskmaster;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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


import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class AddTask extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //public AppDatabase taskDatabase;
    String string="";
    private String imgName;
    private Uri imgData;
    private Intent chooseFile;

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
        Spinner newSpiner=findViewById(R.id.spinnerr);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.hello,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        newSpiner.setAdapter(adapter);
        newSpiner.setOnItemSelectedListener(this);

        // Upload image

        Button uploadImage=findViewById(R.id.upload);
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
                chooseFile.setType("*/*");
                chooseFile = Intent.createChooser(chooseFile, "Choose a file");
                startActivityForResult(chooseFile, 1234);
            }
        });


        // get All Teams

        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());
            Log.i("TaskMaster", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("TaskMaster", "Could not initialize Amplify", error);
        }

        Map<String, String> teamList = new HashMap<>();
        Amplify.API.query(
                ModelQuery.list(com.amplifyframework.datastore.generated.model.Team.class),
                response -> {

                    Log.i("response", response.toString());
                    for (Team oneTeam : response.getData()) {
                        teamList.put(oneTeam.getName(), oneTeam.getId());

                    }
                },
                error -> Log.e("TaskMaster", error.toString(), error)
        );



        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (imgData != null) {
                    try {
                        InputStream exampleInputStream = getContentResolver().openInputStream(imgData);
                        Amplify.Storage.uploadInputStream(
                                imgName,
                                exampleInputStream,
                                result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                                storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
                        );
                    } catch (FileNotFoundException error) {
                        Log.e("MyAmplifyApp", "Could not find file to open for input stream.", error);
                    }
                }

                String myTitel1 = taskTitle.getText().toString();
                String taskDcrep = taskDecrep.getText().toString();
                String stateTas = taskState.getText().toString();


//                Toast.makeText(getApplicationContext(), "hi from add", Toast.LENGTH_LONG).show();
//
//                Task taskModel = Task.builder()
//                        .title(myTitel1)
//                        .body(taskDcrep)
//                        .state(stateTas)
//                        .build();
//
//
//                Amplify.API.mutate(
//                        ModelMutation.create(taskModel),
//                        response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
//                        error -> Log.e("MyAmplifyApp", "Create failed", error));

                ///////////////

                Amplify.API.query(
                        ModelQuery.get(Team.class, teamList.get(string)),
                        response1 -> {
                            Log.i("response1", response1.getData().toString());

                            Task task = Task.builder()
                                    .title(myTitel1)
                                    .body(taskDcrep)
                                    .state(stateTas)
                                    .team(response1.getData())
                                    .fileKey(imgName)
                                    .build();

                            Amplify.API.mutate(
                                    ModelMutation.create(task),
                                    response3 -> Log.i("TaskMaster", "Added Task with id: " + response3.getData().getId()),
                                    error -> Log.e("TaskMaster", "Create failed", error));
                        }, error -> Log.e("TaskMaster", error.toString(), error)
                );






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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File file = new File(data.getData().getPath());
        imgName = file.getName();
        imgData = data.getData();
    }
}