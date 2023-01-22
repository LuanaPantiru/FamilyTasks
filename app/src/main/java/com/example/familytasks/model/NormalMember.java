package com.example.familytasks.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NormalMember extends Member{

    private long familyGroupId;

    public NormalMember(long userId, String userNickname, long familyGroupId) {
        super(userId, userNickname);
        this.familyGroupId = familyGroupId;
    }

    public long getFamilyGroupId() {
        return familyGroupId;
    }

    public void setFamilyGroupId(long familyGroupId) {
        this.familyGroupId = familyGroupId;
    }

    @Override
    public boolean isAdmin() {
        return false;
    }
}
