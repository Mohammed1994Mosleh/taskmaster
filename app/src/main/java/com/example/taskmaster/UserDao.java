package com.example.taskmaster;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM taskModel")
    List<TaskModel> getAll();

    @Query("SELECT * FROM taskModel WHERE id=(:id)")
    TaskModel findById(int id);

    @Insert
    void insert(TaskModel task);


}
