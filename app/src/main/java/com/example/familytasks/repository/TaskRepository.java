package com.example.familytasks.repository;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.ApplicationController;
import com.example.familytasks.async.FamilyGroupInsert;
import com.example.familytasks.async.GetAdminById;
import com.example.familytasks.async.GetFamilyGroupByFamilyId;
import com.example.familytasks.async.TaskInsert;
import com.example.familytasks.model.AdminMember;
import com.example.familytasks.model.FamilyGroup;
import com.example.familytasks.model.Task;

import java.util.concurrent.ExecutionException;

public class TaskRepository {
    private final AppDatabase appDatabase;

    public TaskRepository(){
        appDatabase = ApplicationController.getAppDatabase();
    }

    public Long createTask(Task task){
        try {
            return new TaskInsert(appDatabase).execute(task).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public FamilyGroup getFamilyGroupByFamilyId(long familyId){
//        try {
//            FamilyGroup familyGroup = new GetFamilyGroupByFamilyId(appDatabase).execute(familyId).get();
//            AdminMember adminMember = new GetAdminById(appDatabase).execute(familyGroup.getAdminMemberId()).get();
//            familyGroup.setAdminMember(adminMember);
//            return familyGroup;
//        } catch (ExecutionException | InterruptedException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

}
