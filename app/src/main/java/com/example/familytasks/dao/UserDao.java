package com.example.familytasks.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.familytasks.model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User... user);
    @Query("select * from user")
    List<User> getAll();
    @Query("select * from user where username = :username")
    User findByUsername(String username);
    @Query("select * from user where email = :email")
    User findByEmail(String email);
    @Update
    void updateUsers(User...users);
}
