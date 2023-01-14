package com.example.familytasks.async;

import android.os.AsyncTask;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.dao.UserDao;
import com.example.familytasks.model.User;

public class UserInsert extends AsyncTask<User, Void, Long> {

    private final AppDatabase appDatabase;

    public UserInsert(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    protected Long doInBackground(User... user) {
        UserDao userDao = appDatabase.userDao();
//        if (userDao.findByUsername(user.getUserName()) != null) {
//            return "Username already exists.";
//        }
//        if (userDao.findByEmail(user.getEmail()) != null) {
//            return "Already exists an account associated with this email address.";
//        }
        return userDao.insert(user[0]);
    }

    @Override
    protected void onPostExecute(Long result) {
        super.onPostExecute(result);
    }
}
