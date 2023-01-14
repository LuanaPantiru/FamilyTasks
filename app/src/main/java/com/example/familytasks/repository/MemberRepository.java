package com.example.familytasks.repository;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.ApplicationController;
import com.example.familytasks.async.AdminMemberInsert;
import com.example.familytasks.async.GetFamilyGroupsByUserId;
import com.example.familytasks.async.NormalMemberInsert;
import com.example.familytasks.model.AdminMember;
import com.example.familytasks.model.FamilyGroup;
import com.example.familytasks.model.Member;
import com.example.familytasks.model.NormalMember;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MemberRepository {
    private final AppDatabase appDatabase;

    public MemberRepository(){
        appDatabase = ApplicationController.getAppDatabase();
    }

    public Long insertMember(Member member){
        try {
            if(member.isAdmin()){
                return new AdminMemberInsert(appDatabase).execute((AdminMember) member).get();
            }else{
                return new NormalMemberInsert(appDatabase).execute((NormalMember) member).get();
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<FamilyGroup> getFamilyGroup(long userId){
        try {
            return new GetFamilyGroupsByUserId(appDatabase).execute(userId).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
