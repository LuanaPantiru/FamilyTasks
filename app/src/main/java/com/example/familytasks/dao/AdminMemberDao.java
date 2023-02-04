package com.example.familytasks.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.familytasks.model.AdminMember;
import com.example.familytasks.model.FamilyGroup;
import com.example.familytasks.model.NormalMember;
import com.example.familytasks.model.User;

@Dao
public interface AdminMemberDao {
    @Insert
    long insert(AdminMember member);
    @Query("select * from adminmember where memberid = :id")
    AdminMember getAdminById(long id);
    @Delete
    void delete(AdminMember group);
}
