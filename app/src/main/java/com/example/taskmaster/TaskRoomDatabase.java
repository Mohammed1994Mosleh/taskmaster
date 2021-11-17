package com.example.taskmaster;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class} ,version = 2)
public abstract class TaskRoomDatabase extends RoomDatabase {

    public abstract TaskDao taskDao();

    public static  TaskRoomDatabase taskRoomInstance;

     static TaskRoomDatabase getData(final Context context) {
         if (taskRoomInstance == null) {
             synchronized (TaskRoomDatabase.class) {
                 if (taskRoomInstance == null) {
                     taskRoomInstance = Room.databaseBuilder(context.getApplicationContext(), TaskRoomDatabase.class, "task").build();
                 }
             }
         }
         return taskRoomInstance;
     }
     }

