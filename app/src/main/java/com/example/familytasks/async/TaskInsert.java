package com.example.familytasks.async;

import android.os.AsyncTask;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.dao.FamilyGroupDao;
import com.example.familytasks.dao.NormalMemberDao;
import com.example.familytasks.dao.TaskDao;
import com.example.familytasks.model.FamilyGroup;
import com.example.familytasks.model.Task;
import com.example.familytasks.model.NormalMember;

public class TaskInsert extends AsyncTask<Task, Void, Long> {

    private final AppDatabase appDatabase;

    public TaskInsert(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    protected Long doInBackground(Task...tasks) {
        TaskDao taskDao = appDatabase.taskDao();
        return taskDao.insert(tasks[0]);
    }

    @Override
    protected void onPostExecute(Long result) {
        super.onPostExecute(result);
    }
}
