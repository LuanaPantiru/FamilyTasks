package com.example.familytasks;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AddTask extends AppCompatActivity {

    private EditText taskNameEditText;
    private EditText taskDescriptionEditText;
    private Button createTaskButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);

        taskNameEditText = (EditText) findViewById(R.id.task_title_edit_text);
        taskDescriptionEditText = (EditText) findViewById(R.id.task_description_edit_text);
        createTaskButton = (Button) findViewById(R.id.save_task_button);

        String[] spinnerEntries = {"Option 1", "Option 2", "Option 3"};
        Spinner statusSpinner = (Spinner) findViewById(R.id.status_spinner);
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerEntries);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(statusAdapter);

        Spinner asigneeSpinner = (Spinner) findViewById(R.id.taskAsignee_spinner);
        ArrayAdapter<String> asigneeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerEntries);
        asigneeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        asigneeSpinner.setAdapter(asigneeAdapter);

        createTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = taskNameEditText.getText().toString();
                String taskDescription = taskDescriptionEditText.getText().toString();
                // Add code here to save the task to a database or other storage
            }

    });
    }
}
