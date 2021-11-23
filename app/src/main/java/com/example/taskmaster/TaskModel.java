package com.example.taskmaster;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TaskModel {


    @PrimaryKey(autoGenerate = true)
    private int id;
    public String title;
    public String body;
    public String state;

    public TaskModel(String title, String body, String state) {
        this.title = title;
        this.body = body;
        this.state = state;
    }
    public TaskModel(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
