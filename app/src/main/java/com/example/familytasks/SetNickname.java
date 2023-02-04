package com.example.familytasks;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.familytasks.model.FamilyGroup;
import com.example.familytasks.model.Member;
import com.example.familytasks.model.NormalMember;
import com.example.familytasks.model.User;
import com.example.familytasks.model.enums.FamilyMemberNickname;
import com.example.familytasks.repository.MemberRepository;
import com.example.familytasks.util.InteractionsBetweenScreens;
import com.example.familytasks.util.impl.InteractionBetweenScreensImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class SetNickname extends AppCompatActivity implements OnItemSelectedListener {

    private List<NormalMember> newMembers = new ArrayList<>();
    private List<User> usersToBecomeMembers;
    private long familyId;
    private MemberRepository memberRepository = new MemberRepository();
    private InteractionsBetweenScreens interactionsBetweenScreens = new InteractionBetweenScreensImpl();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_nickname);
        Bundle bundle = getIntent().getExtras();
        usersToBecomeMembers = bundle.getParcelableArrayList("listUser");
        familyId = bundle.getLong("familyId");
        long userId = bundle.getLong("userLogIn");
        createListOfUser();
        Button save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(newMembers.size() != usersToBecomeMembers.size()){
                    Toast.makeText(SetNickname.this,"All needs to have a nickname set!", Toast.LENGTH_SHORT).show();
                }else{
                    newMembers.forEach( m -> memberRepository.insertMember(m));
                    Intent registerScreen = new Intent(SetNickname.this, AllMembers.class);
                    registerScreen.putExtra("familyId",familyId);
                    registerScreen.putExtra("userLogIn",userId);
                    interactionsBetweenScreens.changeScreen(SetNickname.this,registerScreen);
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void createListOfUser() {
        RecyclerView rv = findViewById(R.id.usersToSetNickname);
        SetNicknameAdapter adapter = new SetNicknameAdapter(usersToBecomeMembers,this);
        rv.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onItemSelected(String nickname, User user) {
        if(!Objects.equals(nickname, "None")){
            NormalMember member = newMembers.stream().filter(m -> m.getUserId() == user.getId()).findAny().orElse(null);
            if(member != null){
                member.setUserNickname(nickname);
            }else{
                member = new NormalMember(user.getId(),nickname,familyId);
                newMembers.add(member);
            }
        }
    }
}
