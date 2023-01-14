package com.example.familytasks.async;

import android.os.AsyncTask;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.model.FamilyGroup;
import com.example.familytasks.model.User;

public class GetFamilyGroupByFamilyId extends AsyncTask<Long, Void, FamilyGroup> {
    AppDatabase appDatabase;

    public GetFamilyGroupByFamilyId(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
    }

    @Override
    protected FamilyGroup doInBackground(Long... values) {
        long id = values[0];
        return appDatabase.familyGroupDao().getFamilyGroupsByFamilyId(id);
    }

    @Override
    protected void onPostExecute(FamilyGroup result){
        super.onPostExecute(result);
    }
}
