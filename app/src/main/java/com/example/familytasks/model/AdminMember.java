package com.example.familytasks.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AdminMember extends Member {

    public AdminMember(long userId, String userNickname) {
        super(userId, userNickname);
    }

    @Override
    public boolean isAdmin() {
        return true;
    }

}
