package com.example.familytasks;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.familytasks.model.FamilyGroup;
import com.example.familytasks.model.Member;
import com.example.familytasks.model.User;
import com.example.familytasks.repository.GroupRepository;
import com.example.familytasks.repository.UserRepository;
import com.example.familytasks.util.InteractionsBetweenScreens;
import com.example.familytasks.util.impl.InteractionBetweenScreensImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddMember extends AppCompatActivity implements OnCheckedChangeListener{

    private UserRepository userRepository = new UserRepository();
    private GroupRepository groupRepository = new GroupRepository();
    private List<User> newMembers = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Long> usersWhichAlreadyAreMembers = new ArrayList<>();
    private InteractionsBetweenScreens interactionsBetweenScreens = new InteractionBetweenScreensImpl();
    private UserAdapter adapter;
    private FamilyGroup familyGroup;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_member);
        long familyId = getIntent().getExtras().getLong("familyId");
        familyGroup = groupRepository.getFamilyGroupByFamilyId(familyId);
        usersWhichAlreadyAreMembers = familyGroup.getMembers().stream().map(m -> m.getUser().getId()).collect(Collectors.toList());
        usersWhichAlreadyAreMembers.add(familyGroup.getAdminMember().getUser().getId());
        createListOfUsers();
        EditText search = findViewById(R.id.searchUser);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                users = userRepository.searchUserByUsernameOrEmail(charSequence.toString());
                removeUsers();
                adapter.submit(users);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        Button next = findViewById(R.id.next);
        next.setOnClickListener(view -> {
            Intent registerScreen = new Intent(AddMember.this, SetNickname.class);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("listUser", (ArrayList<? extends Parcelable>) newMembers);
            registerScreen.putExtras(bundle);
            registerScreen.putExtra("familyId",familyId);
            interactionsBetweenScreens.changeScreen(AddMember.this,registerScreen);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void createListOfUsers() {
        RecyclerView rv = findViewById(R.id.users);
        users = userRepository.getUsers();
        removeUsers();
        adapter = new UserAdapter(users, this);
        rv.setAdapter(adapter);
    }

    @Override
    public void onCheckedChangeListener(Boolean isChecked, User user) {
        if(isChecked){
            newMembers.add(user);
        }else{
            newMembers.remove(user);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void removeUsers(){
        for(long id : usersWhichAlreadyAreMembers){
            users.stream().filter(u -> u.getId() == id).findAny().ifPresent(user -> users.remove(user));
        }
    }
}
