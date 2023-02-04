package com.example.familytasks;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.familytasks.model.User;
import com.example.familytasks.repository.UserRepository;
import com.example.familytasks.util.InteractionsBetweenScreens;
import com.example.familytasks.util.impl.InteractionBetweenScreensImpl;

public class MyAccount extends AppCompatActivity {

    private final UserRepository userRepository = new UserRepository();
    private final InteractionsBetweenScreens interactionsBetweenScreens = new InteractionBetweenScreensImpl();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_account);
        long userId = getIntent().getExtras().getLong("userLogIn");
        User user = userRepository.findUserById(userId);
        TextView fullName = findViewById(R.id.fullNameValue);
        fullName.setText(user.getFirstName()+" "+ user.getLastName());
        TextView username = findViewById(R.id.usernameValue);
        username.setText(user.getUserName());
        TextView email = findViewById(R.id.emailValue);
        email.setText(user.getEmail());
        Button update = findViewById(R.id.update);
        update.setOnClickListener(view ->{
            Intent registerScreen = new Intent(MyAccount.this, UpdateAccount.class);
            registerScreen.putExtra("userLogIn", user.getId());
            interactionsBetweenScreens.changeScreen(MyAccount.this,registerScreen);
        });
    }
}
