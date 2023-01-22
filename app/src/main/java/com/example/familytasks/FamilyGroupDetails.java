package com.example.familytasks;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.familytasks.model.FamilyGroup;
import com.example.familytasks.model.enums.Activities;
import com.example.familytasks.repository.GroupRepository;
import com.example.familytasks.util.InteractionsBetweenScreens;
import com.example.familytasks.util.impl.InteractionBetweenScreensImpl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FamilyGroupDetails extends AppCompatActivity implements OnItemClickListener{

    private long familyId;
    private FamilyGroup familyGroup;

    private GroupRepository groupRepository = new GroupRepository();
    private InteractionsBetweenScreens interactionsBetweenScreens = new InteractionBetweenScreensImpl();

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.family_group_details);
        Bundle extra = getIntent().getExtras();
        familyId = extra.getLong("familyId");
        familyGroup = groupRepository.getFamilyGroupByFamilyId(familyId);
        TextView familyName = findViewById(R.id.familyGroupName);
        familyName.setText(familyGroup.getFamilyGroupName()+" Family");
        createListOfActivities();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void createListOfActivities() {
        RecyclerView rv = findViewById(R.id.activities);
        List<String> activities = Arrays.stream(Activities.values()).map(Activities::getActivityName).collect(Collectors.toList());
        ActivityAdapter adapter = new ActivityAdapter(activities,this);
        rv.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Object obj) {
        String activity = (String) obj;
        if(activity.equals(Activities.All_members.getActivityName())){
            Intent registerScreen = new Intent(FamilyGroupDetails.this, AllMembers.class);
            registerScreen.putExtra("familyId",familyId);
            interactionsBetweenScreens.changeScreen(FamilyGroupDetails.this,registerScreen);
        }else{
            Toast.makeText(this,"NU",Toast.LENGTH_SHORT).show();
        }

    }
}
