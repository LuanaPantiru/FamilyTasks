package com.example.familytasks.repository;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.ApplicationController;
import com.example.familytasks.async.FamilyGroupInsert;
import com.example.familytasks.async.GetAdminById;
import com.example.familytasks.async.GetFamilyGroupByFamilyId;
import com.example.familytasks.model.AdminMember;
import com.example.familytasks.model.FamilyGroup;

import java.util.concurrent.ExecutionException;

public class GroupRepository {
    private final AppDatabase appDatabase;

    public GroupRepository(){
        appDatabase = ApplicationController.getAppDatabase();
    }

    public Long createGroup(FamilyGroup group){
        try {
            return new FamilyGroupInsert(appDatabase).execute(group).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public FamilyGroup getFamilyGroupByFamilyId(long familyId){
        try {
            FamilyGroup familyGroup = new GetFamilyGroupByFamilyId(appDatabase).execute(familyId).get();
            AdminMember adminMember = new GetAdminById(appDatabase).execute(familyGroup.getAdminMemberId()).get();
            familyGroup.setAdminMember(adminMember);
            return familyGroup;
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
