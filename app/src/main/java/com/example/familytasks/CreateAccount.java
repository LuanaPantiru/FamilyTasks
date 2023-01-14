package com.example.familytasks;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.familytasks.repository.UserRepository;
import com.example.familytasks.util.InteractionsBetweenScreens;
import com.example.familytasks.util.impl.InteractionBetweenScreensImpl;

public class CreateAccount extends AppCompatActivity {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private TextView logIn;
    private Button register;
    private final UserRepository userRepository = new UserRepository();
    private final InteractionsBetweenScreens interactionsBetweenScreens = new InteractionBetweenScreensImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        logIn = findViewById(R.id.redirectLogIn);

        register = findViewById(R.id.register);

        logIn.setOnClickListener(view -> redirectToLogIn());

        register.setOnClickListener(view -> registerAccount());
    }

    private void registerAccount() {
        firstName = ((EditText) findViewById(R.id.firstName)).getText().toString();
        lastName = ((EditText) findViewById(R.id.lastName)).getText().toString();
        username = ((EditText) findViewById(R.id.username)).getText().toString();
        email = ((EditText) findViewById(R.id.email)).getText().toString();
        password = ((EditText) findViewById(R.id.password)).getText().toString();
        if(!firstName.isEmpty() && !lastName.isEmpty() && !username.isEmpty() && !email.isEmpty() && !password.isEmpty()){
            if(email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
                String result = userRepository.insertUser(firstName,lastName,username,email,password);
                if(result!=null){
                    Toast.makeText(CreateAccount.this, result, Toast.LENGTH_SHORT).show();
                    if(result.equalsIgnoreCase("account created!")){
                        redirectToLogIn();
                    }
                }
            }else{
                Toast.makeText(CreateAccount.this, "Email doesn't have the correct pattern.", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(CreateAccount.this, "All of fields needs to be completed!", Toast.LENGTH_SHORT).show();
        }
    }

    private void redirectToLogIn() {
        Intent registerScreen = new Intent(CreateAccount.this, LogIn.class);
        interactionsBetweenScreens.changeScreen(CreateAccount.this,registerScreen);
    }
}
