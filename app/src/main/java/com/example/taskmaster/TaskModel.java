package com.example.taskmaster;





public class TaskModel {


    public int uid;
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
}
