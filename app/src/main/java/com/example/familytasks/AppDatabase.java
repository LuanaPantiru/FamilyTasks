package com.example.familytasks;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.familytasks.dao.AdminMemberDao;
import com.example.familytasks.dao.FamilyGroupDao;
import com.example.familytasks.dao.NormalMemberDao;
import com.example.familytasks.dao.UserDao;
import com.example.familytasks.model.AdminMember;
import com.example.familytasks.model.FamilyGroup;
import com.example.familytasks.model.NormalMember;
import com.example.familytasks.model.User;

@Database(entities = {User.class,NormalMember.class, AdminMember.class, FamilyGroup.class}, version = 6)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract NormalMemberDao normalMemberDao();
    public abstract AdminMemberDao adminMemberDao();
    public abstract FamilyGroupDao familyGroupDao();
}
