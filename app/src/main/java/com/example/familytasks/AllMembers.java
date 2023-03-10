package com.example.familytasks;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.familytasks.model.AdminMember;
import com.example.familytasks.model.FamilyGroup;
import com.example.familytasks.model.NormalMember;
import com.example.familytasks.repository.GroupRepository;
import com.example.familytasks.repository.MemberRepository;
import com.example.familytasks.util.InteractionsBetweenScreens;
import com.example.familytasks.util.impl.InteractionBetweenScreensImpl;

import java.util.List;

public class AllMembers extends AppCompatActivity{
    private MemberRepository memberRepository = new MemberRepository();
    private GroupRepository groupRepository = new GroupRepository();
    private InteractionsBetweenScreens interactionsBetweenScreens = new InteractionBetweenScreensImpl();
    private FamilyGroup familyGroup;
    private long userId;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_members);
        Bundle extra = getIntent().getExtras();
        long familyId = extra.getLong("familyId");
        userId = extra.getLong("userLogIn");
        familyGroup = groupRepository.getFamilyGroupByFamilyId(familyId);
        AdminMember admin = familyGroup.getAdminMember();
        TextView adminName = findViewById(R.id.memberName);
        adminName.setText(admin.getUser().getFirstName() + " " + admin.getUser().getLastName());
        TextView adminNickname = findViewById(R.id.memberNickname);
        adminNickname.setText(admin.getUserNickname());
        createListOfNormalMembers();
        ImageView imageView = findViewById(R.id.back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllMembers.this, FamilyGroupDetails.class);
                intent.putExtra("userLogIn",userId);
                intent.putExtra("familyId",familyId);
                startActivity(intent);
            }
        });
        Button removeMembers = findViewById(R.id.deleteMember);
        Button addMember = findViewById(R.id.addMember);
        if(admin.getUserId() == userId){
            removeMembers.setVisibility(View.VISIBLE);
            addMember.setVisibility(View.VISIBLE);
        }
        removeMembers.setOnClickListener(view -> {
            if(removeMembers.getVisibility() == View.VISIBLE){
                Intent registerScreen = new Intent(AllMembers.this, RemoveMembers.class);
                registerScreen.putExtra("familyId",familyId);
                registerScreen.putExtra("userLogIn",userId);
                interactionsBetweenScreens.changeScreen(AllMembers.this,registerScreen);
            }
        });
        addMember.setOnClickListener(view -> {
            if(addMember.getVisibility() == View.VISIBLE){
                Intent registerScreen = new Intent(AllMembers.this, AddMember.class);
                registerScreen.putExtra("familyId",familyId);
                registerScreen.putExtra("userLogIn",userId);
                interactionsBetweenScreens.changeScreen(AllMembers.this,registerScreen);
            }
        });
    }

    private void createListOfNormalMembers() {
        RecyclerView rv = findViewById(R.id.membersList);
        List<NormalMember> members = familyGroup.getMembers();
        MemberAdapter adapter = new MemberAdapter(members);
        rv.setAdapter(adapter);
    }
}
