package com.example.familytasks;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.familytasks.dao.UserDao;
import com.example.familytasks.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
