package com.example.familytasks.async;

import android.os.AsyncTask;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.model.User;

public class GetUserByPhoneNumber extends AsyncTask<String, Void, User> {
    AppDatabase appDatabase;

    public GetUserByPhoneNumber(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
    }

    @Override
    protected User doInBackground(String... values) {
        String phoneNumber = values[0];
        return appDatabase.userDao().findByPhoneNumber(phoneNumber);
    }

    @Override
    protected void onPostExecute(User result){
        super.onPostExecute(result);
    }
}
