package com.example.familytasks.async;

import android.os.AsyncTask;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.model.User;

public class UpdateUser extends AsyncTask<User, Void, Integer> {
    AppDatabase appDatabase;

    public UpdateUser(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
    }

    @Override
    protected Integer doInBackground(User... values) {
        appDatabase.userDao().updateUsers(values);
        return values.length;
    }

    @Override
    protected void onPostExecute(Integer result){
        super.onPostExecute(result);
    }
}
