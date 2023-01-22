package com.example.familytasks.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.familytasks.model.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    long insert(Task task);
    @Query("select * from task")
    List<Task> getAllTasks();
    @Query("select * from task where id = :taskId")
    Task getTaskById(long taskId);
    @Query("select * from task where status = :status")
    List<Task> getTasksByStatus(String status);
}
