package com.example.familytasks.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.familytasks.model.NormalMember;
import com.example.familytasks.model.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    long insert(Task task);
    @Query("select * from task where idFamilyGroup=:familyId")
    List<Task> getAllTasks(long familyId);
    @Query("select * from task where id = :taskId")
    Task getTaskById(long taskId);
    @Query(value = "select * from task where status = :status and idFamilyGroup =:familyId")
    List<Task> getTasksByStatus(String status,Long familyId);
    @Delete
    void delete(Task task);
}
