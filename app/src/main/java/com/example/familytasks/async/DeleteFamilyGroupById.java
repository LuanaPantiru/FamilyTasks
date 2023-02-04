package com.example.familytasks.async;

import android.os.AsyncTask;

import com.example.familytasks.AppDatabase;
import com.example.familytasks.dao.AdminMemberDao;
import com.example.familytasks.dao.FamilyGroupDao;
import com.example.familytasks.dao.NormalMemberDao;
import com.example.familytasks.dao.TaskDao;
import com.example.familytasks.model.AdminMember;
import com.example.familytasks.model.FamilyGroup;
import com.example.familytasks.model.NormalMember;
import com.example.familytasks.model.Task;

import java.util.List;

public class DeleteFamilyGroupById extends AsyncTask<Long, Void, Long> {
    AppDatabase appDatabase;

    public DeleteFamilyGroupById(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
    }

    @Override
    protected Long doInBackground(Long... values) {
        long groupId = values[0];
        FamilyGroupDao familyGroupDao = appDatabase.familyGroupDao();
        NormalMemberDao normalMemberDao = appDatabase.normalMemberDao();
        AdminMemberDao adminMemberDao = appDatabase.adminMemberDao();
        TaskDao taskDao = appDatabase.taskDao();
        FamilyGroup familyGroup = familyGroupDao.getFamilyGroupsByFamilyId(groupId);
        if(familyGroup!=null){
            List<NormalMember> members = normalMemberDao.getNormalMembersByFamilyId(familyGroup.getId());
            for(NormalMember member : members){
                normalMemberDao.delete(member);
            }
            AdminMember adminMember = adminMemberDao.getAdminById(familyGroup.getAdminMemberId());
            adminMemberDao.delete(adminMember);
            List<Task> tasks = taskDao.getAllTasks(familyGroup.getId());
            for(Task task : tasks){
                taskDao.delete(task);
            }
            familyGroupDao.delete(familyGroup);
            return groupId;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Long result){
        super.onPostExecute(result);
    }
}
