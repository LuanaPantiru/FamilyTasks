package com.example.familytasks.repository;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.ApplicationController;
import com.example.familytasks.async.AdminMemberInsert;
import com.example.familytasks.async.DeleteMembersById;
import com.example.familytasks.async.GetAdminById;
import com.example.familytasks.async.GetFamilyGroupsByUserId;
import com.example.familytasks.async.GetNormalUserById;
import com.example.familytasks.async.GetUserById;
import com.example.familytasks.async.NormalMemberInsert;
import com.example.familytasks.model.AdminMember;
import com.example.familytasks.model.FamilyGroup;
import com.example.familytasks.model.Member;
import com.example.familytasks.model.NormalMember;
import com.example.familytasks.model.User;

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

    public Member getMemberById(Boolean isAdmin, long memberId){
        try{
            if(isAdmin){
                AdminMember adminMember = new GetAdminById(appDatabase).execute(memberId).get();
                if(adminMember!=null){
                    User user = new GetUserById(appDatabase).execute(adminMember.getUserId()).get();
                    adminMember.setUser(user);
                }
                return adminMember;
            }else{
                NormalMember member = new GetNormalUserById(appDatabase).execute(memberId).get();
                if(member!=null){
                    User user = new GetUserById(appDatabase).execute(member.getUserId()).get();
                    member.setUser(user);
                }
                return member;
            }
        }catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removeMembers(List<Long> membersId){
        for(Long id : membersId){
            new DeleteMembersById(appDatabase).execute(id);
        }
    }
}
