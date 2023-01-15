package com.example.familytasks;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MyTasks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_tasks);

        ListView tasksListView = (ListView) findViewById(R.id.my_tasks_list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<String>(Arrays.asList("task1","task2")));
        tasksListView.setAdapter(adapter);
    }
}