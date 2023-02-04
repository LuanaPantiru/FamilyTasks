package com.example.familytasks.repository;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.ApplicationController;
import com.example.familytasks.async.FamilyGroupInsert;
import com.example.familytasks.async.GetAdminById;
import com.example.familytasks.async.GetFamilyGroupByFamilyId;
import com.example.familytasks.async.GetTaskById;
import com.example.familytasks.async.GetTasks;
import com.example.familytasks.async.GetTasksByStatus;
import com.example.familytasks.async.TaskDeleteById;
import com.example.familytasks.async.TaskInsert;
import com.example.familytasks.model.AdminMember;
import com.example.familytasks.model.FamilyGroup;
import com.example.familytasks.model.Task;

import java.util.ArrayList;
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
    public Long deleteTask(long taskId){
        try {
            return new TaskDeleteById(appDatabase).execute(taskId).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Task>getAllTasks(Long familyId){
        try {

            return new GetTasks(appDatabase).execute(familyId).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Task>getAllMyTasks(Long familyId, long userId){
        try {
            List<Task> allTasks = new GetTasks(appDatabase).execute(familyId).get();
            List<Task> myTask = new ArrayList<>();
            for(Task task : allTasks){
                if(task.getIdUser()==userId){
                    myTask.add(task);
                }
            }
            return myTask;
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Task> getTasksByStatus(String status, Long familyid){
        try {
            return new GetTasksByStatus(appDatabase).execute(status, familyid).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Task getTaskById(long taskId){
        try {
            return new GetTaskById(appDatabase).execute(taskId).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
