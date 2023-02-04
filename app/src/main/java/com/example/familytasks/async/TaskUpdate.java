package com.example.familytasks.async;

import android.os.AsyncTask;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.dao.TaskDao;
import com.example.familytasks.dao.UserDao;
import com.example.familytasks.model.Task;
import com.example.familytasks.model.User;

import java.nio.channels.AsynchronousChannelGroup;

public class TaskUpdate extends AsyncTask<Task,Void,String> {
    private final AppDatabase appDatabase;

    public TaskUpdate(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    protected String doInBackground(Task... tasks) {
        TaskDao taskDao = appDatabase.taskDao();
        try{
            taskDao.update(tasks);
            return "Account update with success";
        }catch (Exception e){
            return "Account can't be updated: " + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}
