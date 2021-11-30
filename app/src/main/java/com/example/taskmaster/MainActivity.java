package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;


import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // AppDatabase taskDatabase;
    String teamId="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());

            Amplify.configure(getApplicationContext());

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }

        Amplify.Auth.signInWithWebUI(
                this,
                result -> Log.i("AuthQuickStart", result.toString()),

                error -> Log.e("AuthQuickStart", error.toString())
        );

        Amplify.Auth.fetchAuthSession(
                result -> Log.i("AmplifyQuickstart1", result.toString()),
                error -> Log.e("AmplifyQuickstart", error.toString())
        );

//        Team teamOne = Team.builder().name("Team1").build();
//        Team teamTwo = Team.builder().name("Team2").build();
//        Team teamThree = Team.builder().name("Team3").build();
//
//        Amplify.API.mutate(
//                ModelMutation.create(teamOne),
//                response -> Log.i("TaskMaster", "Added Todo with id: " + response.getData().getId()),
//                error -> Log.e("TaskMaster", "Create failed", error)
//        );
//        Amplify.API.mutate(
//                ModelMutation.create(teamTwo),
//                response -> Log.i("TaskMaster", "Added Todo with id: " + response.getData().getId()),
//                error -> Log.e("TaskMaster", "Create failed", error)
//        );
//        Amplify.API.mutate(
//                ModelMutation.create(teamThree),
//                response -> Log.i("TaskMaster", "Added Todo with id: " + response.getData().getId()),
//                error -> Log.e("TaskMaster", "Create failed", error)
//        );
//


        // aws amplifier


        //Get Id from SharedPrefrence
         SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
         teamId = sharedPreferences.getString("teamId", "");


         //Current User
        AuthUser logedInUser = Amplify.Auth.getCurrentUser();
        TextView userNameAuth = findViewById(R.id.loguser);
        if (logedInUser != null) {
            userNameAuth.setText(logedInUser.getUsername());
            Log.i("loggg",logedInUser.getUserId().toString() );
        } else {
            userNameAuth.setText("Anonymous");
        }












        //// Add Task Button
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


        //Setting button
        Button settingPage=findViewById(R.id.setting);
        settingPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoSetting=new Intent(MainActivity.this,Settings.class);
                startActivity(gotoSetting);
            }
        });

        //Signout Button
        Button SignOut=findViewById(R.id.signout);
        SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Amplify.Auth.signOut(
                        () -> Log.i("AuthQuickstartsignout", "Signed out successfully"),
                        error -> Log.e("AuthQuickstart", error.toString())
                );
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

        //UserNmae
        Toast.makeText(getApplicationContext(), "onResume Called", Toast.LENGTH_LONG).show();
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userName = sharedPreferences.getString("username","the user didn't add a name yet!");
        TextView userNametext=findViewById(R.id.textUser);
        userNametext.setText(userName);

        //team
        String temaNAme=sharedPreferences.getString("teamName","NO team");
        TextView teamNmaeViwer=findViewById(R.id.teamName);
        teamNmaeViwer.setText(temaNAme);

        teamId = sharedPreferences.getString("teamId", "");
        Log.i("hello1",teamId );



        // tasks

        if(! teamId.equals("")){
            Log.i("hello", teamId);
            RecyclerView recyclerView = findViewById(R.id.allTasksView);
            Handler newHandler=new Handler(Looper.getMainLooper(), new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message message) {
                    recyclerView.getAdapter().notifyDataSetChanged();
                    return false;
                }
            });



            List<Task> tasktoViewd=new ArrayList<>();
            Amplify.API.query(
                    ModelQuery.get(Team.class,teamId),
                    response -> {

                        Log.i("MyAmplifyApp11",response.toString());
                        for (Task taskModel : response.getData().getTasks()) {
                            tasktoViewd.add(taskModel);
                            Log.i("taskkssss",taskModel.toString());
                        }
                        newHandler.sendEmptyMessage(3);

                    },
                    error -> Log.e("MyAmplifyApperror", "Query failure", error)
            );


            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            recyclerView.setAdapter(new TaskAdapter(tasktoViewd));
        }


        AuthUser logedInUser = Amplify.Auth.getCurrentUser();
        TextView userNameAuth = findViewById(R.id.loguser);
        if (logedInUser != null) {
            userNameAuth.setText(logedInUser.getUsername());
        } else {
            userNameAuth.setText("Anonymous");
        }


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