package com.example.familytasks.async;

import android.os.AsyncTask;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.model.FamilyGroup;
import com.example.familytasks.model.NormalMember;

import java.util.List;

public class GetNormalMembersByFamilyId extends AsyncTask<Long, Void, List<NormalMember>> {
    AppDatabase appDatabase;

    public GetNormalMembersByFamilyId(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
    }

    @Override
    protected List<NormalMember> doInBackground(Long... values) {
        long familyId = values[0];
        return appDatabase.normalMemberDao().getNormalMembersByFamilyId(familyId);
    }

    @Override
    protected void onPostExecute(List<NormalMember> result){
        super.onPostExecute(result);
    }
}
