package com.example.familytasks.async;

import android.os.AsyncTask;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.dao.AdminMemberDao;
import com.example.familytasks.model.AdminMember;

public class AdminMemberInsert extends AsyncTask<AdminMember, Void, Long> {

    private final AppDatabase appDatabase;

    public AdminMemberInsert(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    protected Long doInBackground(AdminMember... members) {
        AdminMemberDao memberDao = appDatabase.adminMemberDao();
        return memberDao.insert(members[0]);
    }

    @Override
    protected void onPostExecute(Long result) {
        super.onPostExecute(result);
    }
}
