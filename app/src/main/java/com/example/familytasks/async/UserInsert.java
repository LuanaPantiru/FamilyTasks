package com.example.familytasks.async;

import android.os.AsyncTask;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.dao.UserDao;
import com.example.familytasks.model.User;

public class UserInsert extends AsyncTask<User, Void, String> {

    AppDatabase appDatabase;

    public UserInsert(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    protected String doInBackground(User... users) {
        UserDao userDao = appDatabase.userDao();
        User user = users[0];
        if (userDao.findByUsername(user.getUserName()) != null) {
            return "Username already exists.";
        }
        if (userDao.findByPhoneNumber(user.getPhoneNumber()) != null) {
            return "Already exists an account associated with this phone number.";
        }
        user.setActive(true);
        userDao.insert(user);
        return "Account created.";
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}
