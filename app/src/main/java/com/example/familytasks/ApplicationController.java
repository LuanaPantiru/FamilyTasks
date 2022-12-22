package com.example.familytasks;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public class ApplicationController extends Application {

    private static ApplicationController instance;
    private static AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appDatabase = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"family-tasks").build();
    }

    public static ApplicationController getInstance(){
        return instance;
    }
    public static AppDatabase getAppDatabase(){
        return appDatabase;
    }
}
