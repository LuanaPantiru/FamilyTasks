package com.example.familytasks.async;

import android.os.AsyncTask;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.model.User;

public class GetUserById extends AsyncTask<Long, Void, User> {
    AppDatabase appDatabase;

    public GetUserById(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
    }

    @Override
    protected User doInBackground(Long... values) {
        long id = values[0];
        return appDatabase.userDao().findById(id);
    }

    @Override
    protected void onPostExecute(User result){
        super.onPostExecute(result);
    }
}
