package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Team;

import java.util.HashMap;
import java.util.Map;

public class Settings extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String stringSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        //Spinner

        Spinner newSpiner=findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.hello,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        newSpiner.setAdapter(adapter);
        newSpiner.setOnItemSelectedListener(this);

        //Get teams and Ids

        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());
            Log.i("MasterTask", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MasterTask", "Could not initialize Amplify", error);
        }

        Map< String,String> teamList = new HashMap<>();
        Amplify.API.query(
                ModelQuery.list(com.amplifyframework.datastore.generated.model.Team.class),
                response -> {
                    for (Team team : response.getData()) {

                        teamList.put(team.getName(),team.getId());

                    }
                },
                error -> Log.e("MasterTask", error.toString(), error)
        );

        //Submit

        Button submitUser=findViewById(R.id.submitUsername);
        submitUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userNameInput=findViewById(R.id.username);
                 String userName=userNameInput.getText().toString();
                SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(Settings.this);
                sharedPreferences.edit().putString("username",userName).apply();
                sharedPreferences.edit().putString("teamName",stringSetting).apply();
                sharedPreferences.edit().putString("teamId", teamList.get(stringSetting)).apply();
                Toast.makeText(getApplicationContext(), stringSetting, Toast.LENGTH_LONG).show();

            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
      stringSetting=adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(), stringSetting, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}