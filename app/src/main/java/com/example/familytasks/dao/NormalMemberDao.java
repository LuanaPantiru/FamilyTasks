package com.example.familytasks.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.familytasks.model.AdminMember;
import com.example.familytasks.model.NormalMember;
import com.example.familytasks.model.User;

import java.util.List;

@Dao
public interface NormalMemberDao {
    @Insert
    long insert(NormalMember member);
    @Query("select * from normalmember where familyGroupId = :familyId")
    List<NormalMember> getNormalMembersByFamilyId(long familyId);
    @Query("select * from normalmember where memberid = :id")
    NormalMember getMemberById(long id);
}
