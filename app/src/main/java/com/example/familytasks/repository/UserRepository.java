package com.example.familytasks.repository;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.ApplicationController;
import com.example.familytasks.async.GetUserByEmail;
import com.example.familytasks.async.GetUserById;
import com.example.familytasks.async.GetUserByUsername;
import com.example.familytasks.async.GetUserByUsernameOrEmail;
import com.example.familytasks.async.GetUsers;
import com.example.familytasks.async.UpdateUser;
import com.example.familytasks.async.UserInsert;
import com.example.familytasks.async.UserUpdate;
import com.example.familytasks.model.User;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserRepository {
    private final AppDatabase appDatabase;

    public UserRepository(){
        appDatabase = ApplicationController.getAppDatabase();
    }

    public String insertUser(String firstName, String lastName, String username, String email, String password){
        User user = new User(firstName,lastName,username,email,password);

        if (findUserByUsername(user.getUserName()) != null) {
            return "Username already exists.";
        }
        if (findUserByEmail(user.getEmail()) != null) {
            return "Already exists an account associated with this email address.";
        }
        try {
            user.setActive(true);
            new UserInsert(appDatabase).execute(user).get();
            return "Account created!";
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> getUsers(){
        try {
            return new GetUsers(appDatabase).execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }

    public User findUserByEmail(String email){
        try {
            return new GetUserByEmail(appDatabase).execute(email).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User findUserByUsername(String username){
        try {
            return new GetUserByUsername(appDatabase).execute(username).get();
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

    public User findUserById(long id) {
        try {
            return new GetUserById(appDatabase).execute(id).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> searchUserByUsernameOrEmail(String value){
        try {
            return new GetUserByUsernameOrEmail(appDatabase).execute(value).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateUser(User user){
        new UpdateUser(appDatabase).execute(user);
    }

}
