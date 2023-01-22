package com.example.familytasks;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.familytasks.model.Task;


public class TaskDetails extends AppCompatActivity {

    //private TaskRepository taskRepository = new TaskRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_details);

        Task task = (Task) getIntent().getSerializableExtra("task");

        TextView taskTitle = (TextView) findViewById(R.id.task_title);
        taskTitle.setText(task.getTitle());

        TextView taskDescription = (TextView) findViewById(R.id.task_description);
        taskDescription.setText(task.getDescription());

        TextView taskStatus = (TextView) findViewById(R.id.task_status);
        taskStatus.setText(task.getStatus());

        TextView taskAssignee = (TextView) findViewById(R.id.task_assignee);
        //taskDueDate.setText(task.getAssignee().getUserName());
        taskAssignee.setText("Adi Test");
    }
}