package com.example.familytasks.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.familytasks.model.AdminMember;
import com.example.familytasks.model.FamilyGroup;

import java.util.List;

@Dao
public interface FamilyGroupDao {
    @Insert
    long insert(FamilyGroup familyGroup);
    @Query("select * from familygroup join adminmember on adminMemberId=memberId where userId = :userId")
    List<FamilyGroup> getFamilyGroupsByUserId(long userId);
    @Query("select * from familygroup where id = :familyId")
    FamilyGroup getFamilyGroupsByFamilyId(long familyId);
}
