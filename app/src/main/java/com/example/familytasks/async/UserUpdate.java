package com.example.familytasks.async;

import android.os.AsyncTask;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.dao.UserDao;
import com.example.familytasks.model.User;

import java.nio.channels.AsynchronousChannelGroup;

public class UserUpdate extends AsyncTask<User,Void,String> {
    private final AppDatabase appDatabase;

    public UserUpdate(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    protected String doInBackground(User... users) {
        UserDao userDao = appDatabase.userDao();
        try{
            userDao.updateUsers(users);
            return "Account update with success";
        }catch (Exception e){
            return "Account can't be updated: " + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}
