package com.example.familytasks.async;

import android.os.AsyncTask;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.model.Task;
import com.example.familytasks.model.User;


public class GetTaskById extends AsyncTask<Long, Void, Task> {
    AppDatabase appDatabase;

    public GetTaskById(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
    }

    @Override
    protected Task doInBackground(Long... values) {
        long id = values[0];
        return appDatabase.taskDao().getTaskById(id);
    }

    @Override
    protected void onPostExecute(Task result){
        super.onPostExecute(result);
    }
}
