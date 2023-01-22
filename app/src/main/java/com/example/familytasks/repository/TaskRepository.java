package com.example.familytasks.repository;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.ApplicationController;
import com.example.familytasks.async.FamilyGroupInsert;
import com.example.familytasks.async.GetAdminById;
import com.example.familytasks.async.GetFamilyGroupByFamilyId;
import com.example.familytasks.async.GetTasks;
import com.example.familytasks.async.GetTasksByStatus;
import com.example.familytasks.async.TaskInsert;
import com.example.familytasks.model.AdminMember;
import com.example.familytasks.model.FamilyGroup;
import com.example.familytasks.model.Task;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class TaskRepository {
    private final AppDatabase appDatabase;

    public TaskRepository(){
        appDatabase = ApplicationController.getAppDatabase();
    }

    public Long createTask(Task task){
        try {
            return new TaskInsert(appDatabase).execute(task).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Task>getAllTasks(){
        try {

            return new GetTasks(appDatabase).execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Task> getTasksByStatus(String status){
        try {
            return new GetTasksByStatus(appDatabase).execute(status).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
