package com.example.familytasks.async;

import android.os.AsyncTask;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.model.FamilyGroup;

import java.util.ArrayList;
import java.util.List;

public class GetFamilyGroupsByUserId extends AsyncTask<Long, Void, List<FamilyGroup>> {
    AppDatabase appDatabase;

    public GetFamilyGroupsByUserId(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
    }

    @Override
    protected List<FamilyGroup> doInBackground(Long... values) {
        long userId = values[0];
        List<FamilyGroup> familyGroupList = new ArrayList<>();
        familyGroupList.addAll(appDatabase.familyGroupDao().findFamilyByMembersUsingUserId(userId));
        familyGroupList.addAll(appDatabase.familyGroupDao().findFamilyByAdminUsingUserId(userId));
        return familyGroupList;
    }

    @Override
    protected void onPostExecute(List<FamilyGroup> result){
        super.onPostExecute(result);
    }
}
