package com.example.familytasks;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
    private long userId;

    private InteractionsBetweenScreens interactionsBetweenScreens = new InteractionBetweenScreensImpl();
    private GroupRepository groupRepository = new GroupRepository();

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.family_group_details);
        Bundle extra = getIntent().getExtras();
        familyId = extra.getLong("familyId");
        userId = extra.getLong("userLogIn");
        familyGroup = groupRepository.getFamilyGroupByFamilyId(familyId);
        TextView familyName = findViewById(R.id.familyGroupName);
        familyName.setText(familyGroup.getFamilyGroupName()+" Family");
        createListOfActivities();

        ImageView imageView = findViewById(R.id.back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FamilyGroupDetails.this, MainActivity.class);
                intent.putExtra("id",userId);
                startActivity(intent);
            }
        });

        Button createTaskButton = (Button) findViewById(R.id.createTask);
        createTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FamilyGroupDetails.this, CreateTask.class);
                intent.putExtra("familyId",familyId);
                intent.putExtra("userLogIn",userId);
                startActivity(intent);
            }
        });

        Button deleteGroup = (Button) findViewById(R.id.deleteGroup);
        if(userId == familyGroup.getAdminMember().getUserId()){
            deleteGroup.setVisibility(View.VISIBLE);
        }
        deleteGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(deleteGroup.getVisibility()==View.VISIBLE){
                    // inflate the layout of the popup window
                    LayoutInflater inflater = (LayoutInflater)
                            getSystemService(LAYOUT_INFLATER_SERVICE);
                    View popupView = inflater.inflate(R.layout.popup, null);

                    // create the popup window
                    int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                    int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    boolean focusable = true; // lets taps outside the popup also dismiss it
                    final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                    // show the popup window
                    // which view you pass in doesn't matter, it is only used for the window tolken
                    popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                    Button yes = popupView.findViewById(R.id.yes);
                    Button no = popupView.findViewById(R.id.no);
                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            popupWindow.dismiss();
                            groupRepository.deleteGroup(familyId);
                            Intent registerScreen = new Intent(FamilyGroupDetails.this, MainActivity.class);
                            registerScreen.putExtra("id",userId);
                            interactionsBetweenScreens.changeScreen(FamilyGroupDetails.this,registerScreen);
                        }
                    });

                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            popupWindow.dismiss();
                        }
                    });
                }
            }
        });

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
            registerScreen.putExtra("userLogIn",userId);
            interactionsBetweenScreens.changeScreen(FamilyGroupDetails.this,registerScreen);
        }
        else {
            Intent intent = new Intent(FamilyGroupDetails.this, MyTasks.class);
            if (activity.equals(Activities.My_tasks.getActivityName())){
                intent.putExtra("taskStatus", "");
            }
            if (activity.equals(Activities.All_finish_task.getActivityName()))
                intent.putExtra("taskStatus", "Finished");
            if (activity.equals(Activities.All_to_do_tasks.getActivityName()))
                intent.putExtra("taskStatus", "To do");
            if (activity.equals(Activities.All_in_progress_task.getActivityName()))
                intent.putExtra("taskStatus", "In progress");
            intent.putExtra("familyId",familyId);
            intent.putExtra("userLogIn",userId);
            startActivity(intent);
        }
    }
}
