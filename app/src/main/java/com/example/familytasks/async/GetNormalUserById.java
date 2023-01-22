package com.example.familytasks.async;

import android.os.AsyncTask;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.model.AdminMember;
import com.example.familytasks.model.NormalMember;

public class GetNormalUserById extends AsyncTask<Long, Void, NormalMember> {
    AppDatabase appDatabase;

    public GetNormalUserById(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
    }

    @Override
    protected NormalMember doInBackground(Long... values) {
        long id = values[0];
        return appDatabase.normalMemberDao().getMemberById(id);
    }

    @Override
    protected void onPostExecute(NormalMember result){
        super.onPostExecute(result);
    }
}
