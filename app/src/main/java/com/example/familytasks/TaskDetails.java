package com.example.familytasks;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.familytasks.model.FamilyGroup;
import com.example.familytasks.model.Task;
import com.example.familytasks.model.User;
import com.example.familytasks.repository.GroupRepository;
import com.example.familytasks.repository.TaskRepository;
import com.example.familytasks.repository.UserRepository;
import com.example.familytasks.util.InteractionsBetweenScreens;
import com.example.familytasks.util.impl.InteractionBetweenScreensImpl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class TaskDetails extends AppCompatActivity {

    private TaskRepository taskRepository = new TaskRepository();
    private UserRepository userRepository = new UserRepository();
    private GroupRepository groupRepository = new GroupRepository();

    private final InteractionsBetweenScreens interactionsBetweenScreens = new InteractionBetweenScreensImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_details);

        long taskId = getIntent().getExtras().getLong("taskId");
        Task task = taskRepository.getTaskById(taskId);

        TextView taskTitle = (TextView) findViewById(R.id.task_title);
        taskTitle.setText(task.getTitle());

        TextView taskDescription = (TextView) findViewById(R.id.task_description);
        taskDescription.setText(task.getDescription());

        String[] spinnerStatus = {"To do", "In progress","Finished"};
        Spinner statusSpinner = (Spinner) findViewById(R.id.task_status);
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerStatus);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        statusSpinner.setAdapter(statusAdapter);
        statusSpinner.setBackgroundColor(Color.parseColor(task.getStatusProp().getColor()));

//        Spinner taskStatus =  findViewById(R.id.task_status);
//        taskStatus.setText(task.getStatusProp().getStatusName());
//        taskStatus.setTextColor(Color.parseColor(task.getStatusProp().getColor()));
//        taskStatus.setBackground(ContextCompat.getDrawable(this, task.getStatusProp().getBackground()));



        List<String> spinnerAssignees = Collections.<String> emptyList();

        FamilyGroup familyGroup = groupRepository.getFamilyGroupByFamilyId(task.getIdFamilyGroup());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            spinnerAssignees = familyGroup.getMembers().stream().map(normalMember -> normalMember.getUser().getUserName()).collect(Collectors.toList());
            spinnerAssignees.add(familyGroup.getAdminMember().getUser().getUserName());
        }

        Spinner taskAssignee = findViewById(R.id.task_assignee_spinner);
        ArrayAdapter<String> assigneeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerAssignees);
        assigneeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        taskAssignee.setAdapter(assigneeAdapter);
        User user = userRepository.findUserById(task.getIdUser());
        int position = assigneeAdapter.getPosition(user.getUserName());
        taskAssignee.setSelection(position);

        //User user = userRepository.findUserById(task.getIdUser());
        //taskAssignee.setText(user.getUserName());

        TextView taskPriority = (TextView) findViewById(R.id.task_priority);
        if (task.getPriority() <= 2) {
            taskPriority.setText("Priority: High");
            taskPriority.setTextColor(Color.RED);
        } else if (task.getPriority() <= 5) {
            taskPriority.setText("Priority: Medium");
            taskPriority.setTextColor(Color.YELLOW);
        } else {
            taskPriority.setText("Priority: Low");
            taskPriority.setTextColor(Color.GREEN);
        }
        Button btnDelete = findViewById(R.id.btnDelete);

        btnDelete.setOnClickListener(view ->{
            taskRepository.deleteTask(taskId);
            Intent mainScreen = new Intent(TaskDetails.this,LogIn.class);
            interactionsBetweenScreens.changeScreen(TaskDetails.this,mainScreen);
        });
    }


}