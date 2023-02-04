package com.example.familytasks;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import com.example.familytasks.model.Task;
import com.example.familytasks.repository.TaskRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MyTasks extends AppCompatActivity {

   // private TaskAdapter adapter;
    private TaskRepository taskRepository = new TaskRepository();
    private long familyId;
    private long userId;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_tasks);

        Bundle extra = getIntent().getExtras();
        familyId = extra.getLong("familyId");
        userId = extra.getLong("userLogIn");

        ListView tasksListView = (ListView) findViewById(R.id.my_tasks_list_view);

        List<Task> taskList = new ArrayList<>();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            String taskStatus = getIntent().getExtras().getString("taskStatus");
            if (taskStatus.equals(""))
                taskList = taskRepository.getAllMyTasks(familyId,userId);
            else
                taskList = taskRepository.getTasksByStatus(taskStatus, familyId);

        }
        List<String> objectList = null;
        if(taskList != null || !taskList.isEmpty()){
            objectList = taskList.stream().map(Task::getTitle).collect(Collectors.toList());
        }
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, objectList);
        tasksListView.setAdapter(itemsAdapter);

        List<Task> finalTaskList = taskList;
        tasksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task task = finalTaskList.get(position);
                Intent intent = new Intent(MyTasks.this, TaskDetails.class);
                intent.putExtra("taskId", task.getId());
                startActivity(intent);
            }
        });
    }
}