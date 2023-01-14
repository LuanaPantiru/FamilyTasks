package com.example.familytasks.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NormalMember extends Member{

    public NormalMember(int userId, String userNickname) {
        super(userId, userNickname);
    }

    @Override
    public boolean isAdmin() {
        return false;
    }
}
