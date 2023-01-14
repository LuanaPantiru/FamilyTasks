package com.example.familytasks.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.familytasks.model.NormalMember;
import com.example.familytasks.model.User;

@Dao
public interface NormalMemberDao {
    @Insert
    long insert(NormalMember member);
}
