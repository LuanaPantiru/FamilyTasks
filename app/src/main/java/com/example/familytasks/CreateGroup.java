package com.example.familytasks;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.familytasks.model.AdminMember;
import com.example.familytasks.model.FamilyGroup;
import com.example.familytasks.model.enums.FamilyMemberNickname;
import com.example.familytasks.repository.GroupRepository;
import com.example.familytasks.repository.MemberRepository;
import com.example.familytasks.util.InteractionsBetweenScreens;
import com.example.familytasks.util.impl.InteractionBetweenScreensImpl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiresApi(api = Build.VERSION_CODES.N)
public class CreateGroup extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private String groupName;
    private String nickname;
    private int userId=1;
    private final List<String> familyMemberNicknames = Arrays.stream(FamilyMemberNickname.values()).map(Enum::toString).collect(Collectors.toList());

    private MemberRepository memberRepository = new MemberRepository();
    private GroupRepository groupRepository = new GroupRepository();
    private InteractionsBetweenScreens interactionsBetweenScreens = new InteractionBetweenScreensImpl();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_group);
        Spinner spinnerNickname = (Spinner) findViewById(R.id.nickname);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CreateGroup.this,
                android.R.layout.simple_spinner_item, familyMemberNicknames);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNickname.setAdapter(adapter);
        spinnerNickname.setOnItemSelectedListener(this);

        Button createGroup = findViewById(R.id.createGroup);
        createGroup.setOnClickListener(view -> {
            groupName = ((EditText) findViewById(R.id.familyGroupName)).getText().toString();
            if(!groupName.isEmpty()){
                AdminMember adminMember = new AdminMember(userId, nickname);
                Long id = memberRepository.insertMember(adminMember);
                adminMember.setMemberId(id);
                FamilyGroup familyGroup = new FamilyGroup(groupName, adminMember.getMemberId());
                if(groupRepository.createGroup(familyGroup)!=null){
                    Intent registerScreen = new Intent(CreateGroup.this, MainActivity.class);
                    interactionsBetweenScreens.changeScreen(CreateGroup.this,registerScreen);
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        nickname = familyMemberNicknames.get(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
