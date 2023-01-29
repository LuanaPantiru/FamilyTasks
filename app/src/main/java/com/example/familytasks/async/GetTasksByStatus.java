package com.example.familytasks.async;

import android.os.AsyncTask;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.model.Task;

import java.util.Collections;
import java.util.List;


public class GetTasksByStatus extends AsyncTask<Object, Void, List<Task>> {

    AppDatabase appDatabase;

    public GetTasksByStatus(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
    }

    @Override
    protected List<Task> doInBackground(Object... values) {
        List<Task> tasks;
        try{
            String status = (String)values[0];
            Long familyId = (Long)values[1];
            tasks = appDatabase.taskDao().getTasksByStatus(status,familyId);
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
