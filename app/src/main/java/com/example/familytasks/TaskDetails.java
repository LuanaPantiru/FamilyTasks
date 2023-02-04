package com.example.familytasks;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.familytasks.model.Task;
import com.example.familytasks.model.User;
import com.example.familytasks.repository.TaskRepository;
import com.example.familytasks.repository.UserRepository;


public class TaskDetails extends AppCompatActivity {

    private TaskRepository taskRepository = new TaskRepository();
    private UserRepository userRepository = new UserRepository();

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

        TextView taskStatus = (TextView) findViewById(R.id.task_status);
        taskStatus.setText(task.getStatusProp().getStatusName());
        taskStatus.setTextColor(Color.parseColor(task.getStatusProp().getColor()));
        taskStatus.setBackground(ContextCompat.getDrawable(this,task.getStatusProp().getBackground()));

        TextView taskAssignee = (TextView) findViewById(R.id.task_assignee);
        User user = userRepository.findUserById(task.getIdUser());
        taskAssignee.setText(user.getUserName());

    }
}