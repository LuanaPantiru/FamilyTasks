package com.example.familytasks.async;

import android.os.AsyncTask;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.dao.NormalMemberDao;
import com.example.familytasks.model.NormalMember;

public class NormalMemberInsert extends AsyncTask<NormalMember, Void, Long> {

    private final AppDatabase appDatabase;

    public NormalMemberInsert(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    protected Long doInBackground(NormalMember... members) {
        NormalMemberDao memberDao = appDatabase.normalMemberDao();
        return memberDao.insert(members[0]);
    }

    @Override
    protected void onPostExecute(Long result) {
        super.onPostExecute(result);
    }
}
