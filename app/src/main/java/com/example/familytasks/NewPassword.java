package com.example.familytasks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.familytasks.repository.UserRepository;
import com.example.familytasks.util.InteractionsBetweenScreens;
import com.example.familytasks.util.impl.InteractionBetweenScreensImpl;

public class NewPassword extends AppCompatActivity {
    private String password;
    private Button update;
    private final UserRepository userRepository = new UserRepository();
    private final InteractionsBetweenScreens interactionsBetweenScreens = new InteractionBetweenScreensImpl();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_password);

        update= findViewById(R.id.update);
        update.setOnClickListener(view -> {
            password = ((EditText) findViewById(R.id.password)).getText().toString();
            if(!password.isEmpty()){
                String msg = userRepository.updatePassword(getIntent().getExtras().getString("email"),password);
                Intent registerScreen = new Intent(NewPassword.this, LogIn.class);
                interactionsBetweenScreens.changeScreen(NewPassword.this,registerScreen);
                Toast.makeText(NewPassword.this, msg, Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(NewPassword.this, "All fields need to be completed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
