package com.example.familytasks.async;

import android.os.AsyncTask;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.model.User;

public class GetUserByEmail extends AsyncTask<String, Void, User> {
    AppDatabase appDatabase;

    public GetUserByEmail(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
    }

    @Override
    protected User doInBackground(String... values) {
        String email = values[0];
        return appDatabase.userDao().findByEmail(email);
    }

    @Override
    protected void onPostExecute(User result){
        super.onPostExecute(result);
    }
}
