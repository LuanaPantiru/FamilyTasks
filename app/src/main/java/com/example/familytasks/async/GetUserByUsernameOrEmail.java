package com.example.familytasks.async;

import android.os.AsyncTask;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.model.NormalMember;
import com.example.familytasks.model.User;

import java.util.List;

public class GetUserByUsernameOrEmail extends AsyncTask<String, Void, List<User>> {
    AppDatabase appDatabase;

    public GetUserByUsernameOrEmail(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
    }

    @Override
    protected List<User> doInBackground(String... values) {
        String value = "%"+values[0]+"%";
        return appDatabase.userDao().getUserByUsernameOrEmail(value);
    }

    @Override
    protected void onPostExecute(List<User> result){
        super.onPostExecute(result);
    }
}
