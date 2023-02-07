package com.example.familytasks;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.familytasks.model.User;
import com.example.familytasks.repository.UserRepository;
import com.example.familytasks.util.InteractionsBetweenScreens;
import com.example.familytasks.util.impl.InteractionBetweenScreensImpl;

public class UpdateAccount extends AppCompatActivity {

    private final UserRepository userRepository = new UserRepository();
    private final InteractionsBetweenScreens interactionsBetweenScreens = new InteractionBetweenScreensImpl();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_account);
        long userId = getIntent().getExtras().getLong("userLogIn");
        User user = userRepository.findUserById(userId);
        EditText firstName = findViewById(R.id.firstNameValue);
        firstName.setText(user.getFirstName());
        EditText lastName = findViewById(R.id.lastNameValue);
        lastName.setText(user.getLastName());
        EditText username = findViewById(R.id.usernameValue);
        username.setText(user.getUserName());
        EditText email = findViewById(R.id.emailValue);
        email.setText(user.getEmail());
        ImageView imageView = findViewById(R.id.back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateAccount.this, MyAccount.class);
                intent.putExtra("userLogIn",userId);
                startActivity(intent);
            }
        });
        Button save = findViewById(R.id.save);
        save.setOnClickListener(view ->{
            String newFirstName = firstName.getText().toString();
            String newLastName = lastName.getText().toString();
            String newUsername = username.getText().toString();
            String newEmail = email.getText().toString();
            if(!newFirstName.equals(user.getFirstName()) ||
                    !newLastName.equals(user.getLastName()) ||
                    !newUsername.equals(user.getUserName()) ||
                    !newEmail.equals(user.getEmail())){
                user.setFirstName(newFirstName);
                user.setLastName(newLastName);
                user.setUserName(newUsername);
                user.setEmail(newEmail);
                userRepository.updateUser(user);
            }
            Intent registerScreen = new Intent(UpdateAccount.this, MyAccount.class);
            registerScreen.putExtra("userLogIn", user.getId());
            interactionsBetweenScreens.changeScreen(UpdateAccount.this,registerScreen);
        });
        TextView changePassword = findViewById(R.id.change_password);
        changePassword.setOnClickListener(view -> {
            SharedPreferences preferences = getSharedPreferences("PREFS", MODE_PRIVATE);
            SharedPreferences.Editor edit = preferences.edit();
            edit.clear();
            edit.apply();
            Intent registerScreen = new Intent(UpdateAccount.this, ResetPassword.class);
            interactionsBetweenScreens.changeScreen(UpdateAccount.this,registerScreen);
        });
    }
}
