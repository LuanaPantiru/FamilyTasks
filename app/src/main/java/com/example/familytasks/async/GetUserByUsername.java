package com.example.familytasks.async;

import android.os.AsyncTask;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.model.User;

public class GetUserByUsername extends AsyncTask<String, Void, User> {
    AppDatabase appDatabase;

    public GetUserByUsername(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
    }

    @Override
    protected User doInBackground(String... values) {
        String username = values[0];
        return appDatabase.userDao().findByUsername(username);
    }

    @Override
    protected void onPostExecute(User result){
        super.onPostExecute(result);
    }
}
