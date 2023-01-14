package com.example.familytasks.async;

import android.os.AsyncTask;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.dao.FamilyGroupDao;
import com.example.familytasks.dao.NormalMemberDao;
import com.example.familytasks.model.FamilyGroup;
import com.example.familytasks.model.NormalMember;

public class FamilyGroupInsert extends AsyncTask<FamilyGroup, Void, Long> {

    private final AppDatabase appDatabase;

    public FamilyGroupInsert(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    protected Long doInBackground(FamilyGroup... familyGroups) {
        FamilyGroupDao familyGroupDao = appDatabase.familyGroupDao();
        return familyGroupDao.insert(familyGroups[0]);
    }

    @Override
    protected void onPostExecute(Long result) {
        super.onPostExecute(result);
    }
}
