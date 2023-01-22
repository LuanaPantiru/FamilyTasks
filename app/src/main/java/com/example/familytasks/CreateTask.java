package com.example.familytasks;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.familytasks.model.Task;
import com.example.familytasks.repository.GroupRepository;
import com.example.familytasks.repository.TaskRepository;
import com.example.familytasks.util.InteractionsBetweenScreens;
import com.example.familytasks.util.impl.InteractionBetweenScreensImpl;

public class CreateTask extends AppCompatActivity {

    private EditText taskNameEditText;
    private EditText taskDescriptionEditText;
    private EditText taskPriorityEditText;
    private Spinner taskStatusSpinner;
    private Button saveTaskButton;
    private TaskRepository taskRepository = new TaskRepository();
    private InteractionsBetweenScreens interactionsBetweenScreens = new InteractionBetweenScreensImpl();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);

        taskNameEditText = (EditText) findViewById(R.id.task_title_edit_text);
        taskDescriptionEditText = (EditText) findViewById(R.id.task_description_edit_text);
        saveTaskButton = (Button) findViewById(R.id.save_task_button);
        taskPriorityEditText = (EditText) findViewById(R.id.priority_editText);
        taskStatusSpinner = (Spinner) findViewById(R.id.status_spinner);

        String[] spinnerAssignees = {"Assignee 1", "Assignee 2", "Assignee 3"};
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
                Integer taskPriority = Integer.parseInt(taskPriorityEditText.getText().toString());
                String taskStatus = taskStatusSpinner.getSelectedItem().toString();

                Task task = new Task(taskName,taskDescription,taskPriority,taskStatus);
                if(taskRepository.createTask(task)!=null){
                    Intent familyDetailsScreen = new Intent(CreateTask.this, FamilyGroupDetails.class);
                    interactionsBetweenScreens.changeScreen(CreateTask.this,familyDetailsScreen);
                }
            }

    });
    }
}
