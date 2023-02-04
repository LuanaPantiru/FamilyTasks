package com.example.familytasks;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.familytasks.model.FamilyGroup;
import com.example.familytasks.model.Task;
import com.example.familytasks.repository.GroupRepository;
import com.example.familytasks.repository.TaskRepository;
import com.example.familytasks.repository.UserRepository;
import com.example.familytasks.util.InteractionsBetweenScreens;
import com.example.familytasks.util.MailApi;
import com.example.familytasks.util.impl.InteractionBetweenScreensImpl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CreateTask extends AppCompatActivity {

    private EditText taskNameEditText;
    private EditText taskDescriptionEditText;
    private Button saveTaskButton;
    private TaskRepository taskRepository = new TaskRepository();
    private GroupRepository groupRepository = new GroupRepository();
    private UserRepository userRepository = new UserRepository();
    private InteractionsBetweenScreens interactionsBetweenScreens = new InteractionBetweenScreensImpl();

    private long familyId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);

        Bundle extra = getIntent().getExtras();
        familyId = extra.getLong("familyId");

        taskNameEditText = (EditText) findViewById(R.id.task_title_edit_text);
        taskDescriptionEditText = (EditText) findViewById(R.id.task_description_edit_text);
        saveTaskButton = (Button) findViewById(R.id.save_task_button);

        List<String> spinnerAssignees = Collections.<String> emptyList();
        FamilyGroup familyGroup = groupRepository.getFamilyGroupByFamilyId(familyId);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            spinnerAssignees = familyGroup.getMembers().stream().map(normalMember -> normalMember.getUser().getUserName()).collect(Collectors.toList());
            spinnerAssignees.add(familyGroup.getAdminMember().getUser().getUserName());
        }

        String[] spinnerPriority = {"Low", "Medium","High"};
        Spinner prioritySpinner = (Spinner) findViewById(R.id.priority_spinner);
        ArrayAdapter<String> priorityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerPriority);
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prioritySpinner.setAdapter(priorityAdapter);

        String[] spinnerStatus = {"To do", "In progress","Finished"};
        Spinner statusSpinner = (Spinner) findViewById(R.id.status_spinner);
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerStatus);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(statusAdapter);

        Spinner assigneeSpinner = (Spinner) findViewById(R.id.taskAsignee_spinner);
        ArrayAdapter<String> assigneeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerAssignees);
        assigneeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assigneeSpinner.setAdapter(assigneeAdapter);

        saveTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = taskNameEditText.getText().toString();
                String taskDescription = taskDescriptionEditText.getText().toString();
                String taskPriority = prioritySpinner.getSelectedItem().toString();
                String taskStatus = statusSpinner.getSelectedItem().toString();
                String username = assigneeSpinner.getSelectedItem().toString();

                long idUser = userRepository.findUserByUsername(username).getId();
                long idFamilyGroup=familyId;





                Task task = new Task(taskName,taskDescription,taskPriority,taskStatus,idUser,idFamilyGroup);
                if(taskRepository.createTask(task)!=null){
                    String email = userRepository.findUserById(idUser).getEmail();
                    MailApi mailApi = new MailApi(CreateTask.this,email,"New Task","A new task has been assigned to you");
                    mailApi.execute();

                    Intent familyDetailsScreen = new Intent(CreateTask.this, FamilyGroupDetails.class);
                    familyDetailsScreen.putExtra("familyId",familyId);
                    interactionsBetweenScreens.changeScreen(CreateTask.this,familyDetailsScreen);
                }
            }

    });
    }
}
