package com.example.familytasks;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.familytasks.model.Task;
import com.example.familytasks.repository.TaskRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class MyTasks extends AppCompatActivity {

   // private TaskAdapter adapter;
    private TaskRepository taskRepository = new TaskRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_tasks);

        ListView tasksListView = (ListView) findViewById(R.id.my_tasks_list_view);

        ArrayList<String> objectList = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            String taskStatus = getIntent().getExtras().getString("taskStatus");
            if (taskStatus.equals(""))
                objectList = new ArrayList<String>(taskRepository.getAllTasks().stream().map(Task::getTitle).collect(Collectors.toList()));
            else
                objectList = new ArrayList<String>(taskRepository.getTasksByStatus(taskStatus).stream().map(Task::getTitle).collect(Collectors.toList()));

        }
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, objectList);
        tasksListView.setAdapter(itemsAdapter);

        tasksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task task = taskRepository.getAllTasks().get(position);
                Intent intent = new Intent(MyTasks.this, TaskDetails.class);
                intent.putExtra("task", task);
                startActivity(intent);
            }
        });
    }
}