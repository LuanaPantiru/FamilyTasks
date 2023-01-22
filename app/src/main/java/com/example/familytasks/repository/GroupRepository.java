package com.example.familytasks.repository;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.ApplicationController;
import com.example.familytasks.async.FamilyGroupInsert;
import com.example.familytasks.async.GetAdminById;
import com.example.familytasks.async.GetFamilyGroupByFamilyId;
import com.example.familytasks.async.GetNormalMembersByFamilyId;
import com.example.familytasks.async.GetUserById;
import com.example.familytasks.model.AdminMember;
import com.example.familytasks.model.FamilyGroup;
import com.example.familytasks.model.NormalMember;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class GroupRepository {
    private final AppDatabase appDatabase;
    private final MemberRepository memberRepository = new MemberRepository();

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
            if(familyGroup != null){
                AdminMember adminMember = (AdminMember) memberRepository.getMemberById(true,familyGroup.getAdminMemberId());
                List<NormalMember> members = getNormalMembers(familyId);
                familyGroup.setAdminMember(adminMember);
                familyGroup.setMembers(members);
            }
            return familyGroup;
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<NormalMember> getNormalMembers(long familyId){
        List<NormalMember> members = new ArrayList<>();
        try {
            members = new GetNormalMembersByFamilyId(appDatabase).execute(familyId).get();
            for(NormalMember member : members){
                member.setUser(new GetUserById(appDatabase).execute(member.getUserId()).get());
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return members;
    }

}
