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
import com.example.familytasks.util.InteractionsBetweenScreens;
import com.example.familytasks.util.impl.InteractionBetweenScreensImpl;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private final UserRepository userRepository = new UserRepository();
    private final InteractionsBetweenScreens interactionsBetweenScreens = new InteractionBetweenScreensImpl();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Bundle extra = getIntent().getExtras();
        User user = userRepository.findUserByEmail(extra.getString("email"));
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
            interactionsBetweenScreens.changeScreen(MainActivity.this,registerScreen);
        });
    }
}
