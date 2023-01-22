package com.example.familytasks.async;

import android.os.AsyncTask;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.model.Task;
import com.example.familytasks.model.User;

import java.util.Collections;
import java.util.List;


public class GetTasksByStatus extends AsyncTask<String, Void, List<Task>> {

    AppDatabase appDatabase;

    public GetTasksByStatus(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
    }

    @Override
    protected List<Task> doInBackground(String... values) {
        List<Task> tasks;
        try{
            String status = values[0];
            tasks = appDatabase.taskDao().getTasksByStatus(status);
        }catch (Exception e){
            return Collections.emptyList();
        }
        return tasks;
    }

    @Override
    protected void onPostExecute(List<Task> tasks){
        super.onPostExecute(tasks);
    }
}
