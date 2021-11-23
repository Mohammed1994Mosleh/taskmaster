package com.example.taskmaster;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TaskModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public static AppDatabase taskDatabase;
    static AppDatabase getInstance(Context context){
        if (taskDatabase == null) {
            synchronized (AppDatabase.class) {
                if (taskDatabase == null) {
                    taskDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "task").build();
                }
            }
        }
        return taskDatabase;
    }

}