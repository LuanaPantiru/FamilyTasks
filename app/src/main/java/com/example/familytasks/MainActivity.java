package com.example.familytasks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.familytasks.model.User;
import com.example.familytasks.repository.UserRepository;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private final UserRepository userRepository = new UserRepository();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Bundle extra = getIntent().getExtras();
        User user = userRepository.findUserByPhoneNumber(extra.getString("phone"));
        if(user!=null){
            textView = findViewById(R.id.textView);
            textView.setText(user.getUserName());
        }
        Button logOut = findViewById(R.id.logOut);
        logOut.setOnClickListener(view -> {
            SharedPreferences preferences = getSharedPreferences("PREFS", MODE_PRIVATE);
            SharedPreferences.Editor edit = preferences.edit();
            edit.clear();
            edit.apply();
            Intent registerScreen = new Intent(MainActivity.this, LogIn.class);
            startActivity(registerScreen);
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        });
    }
}
