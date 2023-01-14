package com.example.familytasks.async;

import android.os.AsyncTask;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.model.AdminMember;
import com.example.familytasks.model.FamilyGroup;

public class GetAdminById extends AsyncTask<Long, Void, AdminMember> {
    AppDatabase appDatabase;

    public GetAdminById(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
    }

    @Override
    protected AdminMember doInBackground(Long... values) {
        long id = values[0];
        return appDatabase.adminMemberDao().getAdminById(id);
    }

    @Override
    protected void onPostExecute(AdminMember result){
        super.onPostExecute(result);
    }
}
