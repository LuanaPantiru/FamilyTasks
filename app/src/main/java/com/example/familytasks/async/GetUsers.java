package com.example.familytasks.async;

import android.os.AsyncTask;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.model.User;

import java.util.Collections;
import java.util.List;


public class GetUsers extends AsyncTask<Void, Void, List<User>> {

    AppDatabase appDatabase;

    public GetUsers(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
    }

    @Override
    protected List<User> doInBackground(Void... voids) {
        List<User> users;
        try{
            users = appDatabase.userDao().getAll();
        }catch (Exception e){
            return Collections.emptyList();
        }
        return users;
    }

    @Override
    protected void onPostExecute(List<User> users){
        super.onPostExecute(users);
    }
}
