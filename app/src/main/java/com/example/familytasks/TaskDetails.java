package com.example.familytasks;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.familytasks.model.Task;
import com.example.familytasks.model.User;
import com.example.familytasks.repository.TaskRepository;
import com.example.familytasks.repository.UserRepository;
import com.example.familytasks.util.InteractionsBetweenScreens;
import com.example.familytasks.util.impl.InteractionBetweenScreensImpl;


public class TaskDetails extends AppCompatActivity {

    private TaskRepository taskRepository = new TaskRepository();
    private UserRepository userRepository = new UserRepository();
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

        TextView taskStatus = (TextView) findViewById(R.id.task_status);
        taskStatus.setText(task.getStatusProp().getStatusName());
        taskStatus.setTextColor(Color.parseColor(task.getStatusProp().getColor()));
        taskStatus.setBackground(ContextCompat.getDrawable(this, task.getStatusProp().getBackground()));

        TextView taskAssignee = (TextView) findViewById(R.id.task_assignee);
        User user = userRepository.findUserById(task.getIdUser());
        taskAssignee.setText(user.getUserName());

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