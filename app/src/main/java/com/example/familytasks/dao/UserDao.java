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
    long insert(User user);
    @Query("select * from user")
    List<User> getAll();
    @Query("select * from user where username = :username")
    User findByUsername(String username);
    @Query("select * from user where email = :email")
    User findByEmail(String email);
    @Query("select * from user where id = :id")
    User findById(long id);
    @Query("select * from user where username like :value or email like :value")
    List<User> getUserByUsernameOrEmail(String value);
    @Update
    void updateUsers(User...users);
}
