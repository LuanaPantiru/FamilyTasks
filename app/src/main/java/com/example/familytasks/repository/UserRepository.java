package com.example.familytasks.repository;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.ApplicationController;
import com.example.familytasks.async.GetUserByEmail;
import com.example.familytasks.async.GetUsers;
import com.example.familytasks.async.UserInsert;
import com.example.familytasks.async.UserUpdate;
import com.example.familytasks.model.User;

import java.util.concurrent.ExecutionException;

public class UserRepository {
    private final AppDatabase appDatabase;

    public UserRepository(){
        appDatabase = ApplicationController.getAppDatabase();
    }

    public String insertUser(String firstName, String lastName, String username, String email, String password){
        User user = new User(firstName,lastName,username,email,password);
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

    public User findUserByEmail(String email){
        try {
            return new GetUserByEmail(appDatabase).execute(email).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deactivateAccount(String email){
        User user = findUserByEmail(email);
        if(user!=null){
            user.setActive(false);
            try{
                new UserUpdate(appDatabase).execute(user).get();
            }catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String updatePassword(String email, String password){
        User user = findUserByEmail(email);
        if(user!=null){
            user.setPassword(password);
            if(!user.getActive()){
                user.setActive(true);
            }
            try{
                return new UserUpdate(appDatabase).execute(user).get();
            }catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
                return null;
            }

        }else{
            return "Doesn't exists an account associated with this email address.";
        }
    }
}
