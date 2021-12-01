package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;

import java.io.File;

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

        ImageView imageTask=findViewById(R.id.keyImage);

        Intent intent = getIntent();

        //get Image from storage
        if (intent.getExtras().getString("taskImage") != null) {
            Amplify.Storage.downloadFile(
                    intent.getExtras().getString("taskImage"),
                    new File(getApplicationContext().getFilesDir() + "/" + intent.getExtras().getString("taskImage") + ".jpg"),
                    result -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(result.getFile().getPath());
                        imageTask.setImageBitmap(bitmap);
                        Log.i("MyAmplifyApp", "Successfully downloaded: " + result.getFile().getName());
                    },
                    error -> Log.e("MyAmplifyApp", "Download Failure", error)
            );
        }


    }
}