package com.example.familytasks.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public abstract class Member {
    @PrimaryKey(autoGenerate = true)
    private long memberId;
    private long userId;
    @Ignore
    private User user;
    private String userNickname;

    public Member(long userId, String userNickname) {
        this.userId = userId;
        this.userNickname = userNickname;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public abstract boolean isAdmin();

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
