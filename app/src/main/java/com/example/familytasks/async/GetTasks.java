package com.example.familytasks.async;

import android.os.AsyncTask;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.model.Task;
import com.example.familytasks.model.User;

import java.util.Collections;
import java.util.List;


public class GetTasks extends AsyncTask<Void, Void, List<Task>> {

    AppDatabase appDatabase;

    public GetTasks(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
    }

    @Override
    protected List<Task> doInBackground(Void... voids) {
        List<Task> tasks;
        try{
            tasks = appDatabase.taskDao().getAllTasks();
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
