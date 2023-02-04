package com.example.familytasks;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.familytasks.model.FamilyGroup;
import com.example.familytasks.model.NormalMember;
import com.example.familytasks.repository.GroupRepository;
import com.example.familytasks.repository.MemberRepository;
import com.example.familytasks.util.InteractionsBetweenScreens;
import com.example.familytasks.util.impl.InteractionBetweenScreensImpl;

import java.util.ArrayList;
import java.util.List;

public class RemoveMembers extends AppCompatActivity implements OnCheckedChangeListener{

    private FamilyGroup familyGroup;
    private GroupRepository groupRepository = new GroupRepository();
    private List<Long> membersToRemove = new ArrayList<>();
    private MemberRepository memberRepository = new MemberRepository();
    private InteractionsBetweenScreens interactionsBetweenScreens = new InteractionBetweenScreensImpl();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remove_members);
        long familyId = getIntent().getExtras().getLong("familyId");
        familyGroup = groupRepository.getFamilyGroupByFamilyId(familyId);
        createListOfMembers();
        Button next = findViewById(R.id.next);
        next.setOnClickListener(view -> {
            memberRepository.removeMembers(membersToRemove);
            Intent registerScreen = new Intent(RemoveMembers.this, AllMembers.class);
            Bundle bundle = new Bundle();
            registerScreen.putExtras(bundle);
            registerScreen.putExtra("familyId",familyId);
            registerScreen.putExtra("userLogIn",familyGroup.getAdminMember().getUserId());
            interactionsBetweenScreens.changeScreen(RemoveMembers.this,registerScreen);
        });
    }

    private void createListOfMembers() {
        RecyclerView rv = findViewById(R.id.members);
        RemoveMemberAdapter adapter = new RemoveMemberAdapter(familyGroup.getMembers(), this);
        rv.setAdapter(adapter);
    }

    @Override
    public void onCheckedChangeListener(Boolean isChecked, Object obj) {
        NormalMember member = (NormalMember) obj;
        List<NormalMember> memberList = familyGroup.getMembers();
        if(memberList.contains(member)){
            memberList.remove(member);
            membersToRemove.add(member.getMemberId());
        }else{
            memberList.add(member);
            membersToRemove.remove(member.getMemberId());
        }
    }
}
