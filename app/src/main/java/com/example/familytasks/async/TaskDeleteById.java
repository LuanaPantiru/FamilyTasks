package com.example.familytasks.async;

import android.os.AsyncTask;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.dao.NormalMemberDao;
import com.example.familytasks.dao.TaskDao;
import com.example.familytasks.model.NormalMember;
import com.example.familytasks.model.Task;


public class TaskDeleteById extends AsyncTask<Long, Void, Long> {
    AppDatabase appDatabase;

    public TaskDeleteById (AppDatabase appDatabase){
        this.appDatabase = appDatabase;
    }

    @Override
    protected Long doInBackground(Long... values) {
        long taskId = values[0];
        TaskDao taskDao = appDatabase.taskDao();
        Task task = taskDao.getTaskById(taskId);
        if(task != null){
            taskDao.delete(task);
            return taskId;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Long result){
        super.onPostExecute(result);
    }
}
