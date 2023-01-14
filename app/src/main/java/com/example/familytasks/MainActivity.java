package com.example.familytasks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.familytasks.model.AdminMember;
import com.example.familytasks.model.FamilyGroup;
import com.example.familytasks.model.NormalMember;
import com.example.familytasks.model.User;
import com.example.familytasks.repository.MemberRepository;
import com.example.familytasks.repository.UserRepository;
import com.example.familytasks.util.InteractionsBetweenScreens;
import com.example.familytasks.util.impl.InteractionBetweenScreensImpl;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener{

    private final UserRepository userRepository = new UserRepository();
    private final InteractionsBetweenScreens interactionsBetweenScreens = new InteractionBetweenScreensImpl();
    private final MemberRepository memberRepository = new MemberRepository();
    private long userId;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Bundle extra = getIntent().getExtras();
        userId = extra.getLong("id");
        createListOfFamilyGroups();
        Button logOut = findViewById(R.id.logOut);
        logOut.setOnClickListener(view -> {
            SharedPreferences preferences = getSharedPreferences("PREFS", MODE_PRIVATE);
            SharedPreferences.Editor edit = preferences.edit();
            edit.clear();
            edit.apply();
            Intent registerScreen = new Intent(MainActivity.this, LogIn.class);
            interactionsBetweenScreens.changeScreen(MainActivity.this,registerScreen);
        });
        Button createGroup = findViewById(R.id.createGroup);
        createGroup.setOnClickListener(view ->{
            Intent registerScreen = new Intent(MainActivity.this, CreateGroup.class);
            interactionsBetweenScreens.changeScreen(MainActivity.this,registerScreen);
        });
    }

    private void createListOfFamilyGroups() {
        RecyclerView rv = findViewById(R.id.groups);
        List<FamilyGroup> familyGroupList = memberRepository.getFamilyGroup(userId);
        FamilyGroupAdapter adapter = new FamilyGroupAdapter(familyGroupList,this);
        rv.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Object obj) {
        Intent registerScreen = new Intent(MainActivity.this, FamilyGroupDetails.class);
        registerScreen.putExtra("familyId",((FamilyGroup) obj).getId());
        interactionsBetweenScreens.changeScreen(MainActivity.this,registerScreen);
    }
}
