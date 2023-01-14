package com.example.familytasks.model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class FamilyGroup {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "family_group_name")
    private String familyGroupName;
    private long adminMemberId;
    @Ignore
    private AdminMember adminMember;
    @Ignore
    private List<NormalMember> normalMembers;

    public FamilyGroup(String familyGroupName,long adminMemberId) {
        this.familyGroupName = familyGroupName;
        this.adminMemberId = adminMemberId;
    }

    public String getFamilyGroupName() {
        return familyGroupName;
    }

    public void setFamilyGroupName(String familyGroupName) {
        this.familyGroupName = familyGroupName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AdminMember getAdminMember() {
        return adminMember;
    }

    public void setAdminMember(AdminMember adminMember) {
        this.adminMember = adminMember;
    }

    public List<NormalMember> getNormalMembers() {
        return normalMembers;
    }

    public void setNormalMembers(List<NormalMember> normalMembers) {
        this.normalMembers = normalMembers;
    }

    public long getAdminMemberId() {
        return adminMemberId;
    }

    public void setAdminMemberId(long adminMemberId) {
        this.adminMemberId = adminMemberId;
    }
}
