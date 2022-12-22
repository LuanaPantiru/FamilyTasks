package com.example.familytasks.repository;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.ApplicationController;
import com.example.familytasks.async.GetUserByPhoneNumber;
import com.example.familytasks.async.GetUsers;
import com.example.familytasks.async.UserInsert;
import com.example.familytasks.model.User;

import java.util.concurrent.ExecutionException;

public class UserRepository {
    private final AppDatabase appDatabase;

    public UserRepository(){
        appDatabase = ApplicationController.getAppDatabase();
    }

    public String insertUser(String firstName, String lastName, String username, String phone, String password){
        User user = new User(firstName,lastName,username,phone,password);
        try {
            return new UserInsert(appDatabase).execute(user).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void getUsers(){
        new GetUsers(appDatabase).execute();
    }

    public User findUserByPhoneNumber(String phoneNumber){
        try {
            return new GetUserByPhoneNumber(appDatabase).execute(phoneNumber).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
