package com.example.familytasks;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
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
import com.example.familytasks.util.MailApi;
import com.example.familytasks.util.impl.InteractionBetweenScreensImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class TaskDetails extends AppCompatActivity {

    private TaskRepository taskRepository = new TaskRepository();
    private UserRepository userRepository = new UserRepository();
    private GroupRepository groupRepository = new GroupRepository();
    private Spinner statusSpinner;
    private Task task;
    private final List<String> spinnerStatusValues = Arrays.asList("To do", "In progress", "Finished");
    private final List<String> spinnerPriorityValues = Arrays.asList("Low", "Medium", "High");

    private final InteractionsBetweenScreens interactionsBetweenScreens = new InteractionBetweenScreensImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_details);

        long taskId = getIntent().getExtras().getLong("taskId");
        long userId = getIntent().getExtras().getLong("userLogIn");
        String taskStatus = getIntent().getExtras().getString("taskStatus");
        task = taskRepository.getTaskById(taskId);

        TextView taskTitle = (TextView) findViewById(R.id.task_title);
        taskTitle.setText(task.getTitle());

        TextView taskDescription = (TextView) findViewById(R.id.task_description);
        taskDescription.setText(task.getDescription());

        ImageView imageView = findViewById(R.id.back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaskDetails.this, MyTasks.class);
                intent.putExtra("userLogIn",userId);
                intent.putExtra("familyId",task.getIdFamilyGroup());
                intent.putExtra("taskStatus",taskStatus);
                startActivity(intent);
            }
        });


        statusSpinner = (Spinner) findViewById(R.id.task_status);
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(this, R.layout.spinner_status, spinnerStatusValues);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        statusSpinner.setAdapter(statusAdapter);
        int index = spinnerStatusValues.indexOf(task.getStatus());
        statusSpinner.setSelection(index);
        statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String newStatus = spinnerStatusValues.get(i);
                task.setStatus(newStatus);
                TextView textView = (TextView) adapterView.getChildAt(0);
                textView.setTextColor(Color.parseColor(task.getTextColor()));
                statusSpinner.setBackground(ContextCompat.getDrawable(TaskDetails.this, task.getBackground()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        List<String> spinnerAssignees = Collections.<String>emptyList();

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

        Spinner taskPriority = findViewById(R.id.task_priority);
        ArrayAdapter<String> priorityAdapter = new ArrayAdapter<String>(this, R.layout.spinner_priority, spinnerPriorityValues);
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        taskPriority.setAdapter(priorityAdapter);
        taskPriority.setSelection(spinnerPriorityValues.indexOf(task.getPriority()));
        taskPriority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String newPriority = spinnerPriorityValues.get(i);
                task.setPriority(newPriority);
                TextView textView = (TextView) adapterView.getChildAt(0);
                if (task.getPriority().equals("High")) {
                    textView.setTextColor(Color.RED);
                } else if (task.getPriority().equals("Medium")) {
                    textView.setTextColor(Color.YELLOW);
                } else {
                    textView.setTextColor(Color.GREEN);
                }
            }

                @Override
                public void onNothingSelected (AdapterView < ? > adapterView){

                }
            });

            Button btnDelete = findViewById(R.id.btnDelete);
            if(familyGroup.getAdminMember().getUserId()==userId){
                btnDelete.setVisibility(View.VISIBLE);
            }

        btnDelete.setOnClickListener(view ->

            {
                taskRepository.deleteTask(taskId);
                Intent mainScreen = new Intent(TaskDetails.this, FamilyGroupDetails.class);
                mainScreen.putExtra("familyId",task.getIdFamilyGroup());
                mainScreen.putExtra("userLogIn",task.getIdUser());
                interactionsBetweenScreens.changeScreen(TaskDetails.this, mainScreen);
            });
            Button btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(view ->

            {
                task.setTitle(taskTitle.getText().toString());
                task.setDescription(taskDescription.getText().toString());
                long oldUser=task.getIdUser();
                User newUser=userRepository.findUserByUsername(taskAssignee.getSelectedItem().toString());
                if(oldUser!=newUser.getId()){
                    task.setIdUser(newUser.getId());
                    MailApi mailApi = new MailApi(TaskDetails.this, newUser.getEmail(), "New Task","A new task has been assigned to you");
                    mailApi.execute();
                }

//            Task newTask = new Task(, , task.getPriority(), task.getStatusProp().getStatusName(), 1 , task.getIdFamilyGroup()); //

                taskRepository.updateTask(task);
                Intent mainScreen = new Intent(TaskDetails.this, FamilyGroupDetails.class);
                mainScreen.putExtra("familyId",task.getIdFamilyGroup());
                mainScreen.putExtra("userLogIn",userId);
                interactionsBetweenScreens.changeScreen(TaskDetails.this, mainScreen);
            });

        }
    }