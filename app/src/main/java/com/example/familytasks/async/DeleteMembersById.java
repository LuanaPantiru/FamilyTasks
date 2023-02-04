package com.example.familytasks.async;

import android.os.AsyncTask;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.dao.NormalMemberDao;
import com.example.familytasks.model.NormalMember;


public class DeleteMembersById extends AsyncTask<Long, Void, Long> {
    AppDatabase appDatabase;

    public DeleteMembersById(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
    }

    @Override
    protected Long doInBackground(Long... values) {
        long memberId = values[0];
        NormalMemberDao normalMemberDao = appDatabase.normalMemberDao();
        NormalMember member = normalMemberDao.getMemberById(memberId);
        if(member != null){
            normalMemberDao.delete(member);
            return memberId;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Long result){
        super.onPostExecute(result);
    }
}
