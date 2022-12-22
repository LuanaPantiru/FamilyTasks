package com.example.familytasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.familytasks.repository.UserRepository;

public class CreateAccount extends AppCompatActivity {

    private String firstName;
    private String lastName;
    private String username;
    private String phone;
    private String password;
    private TextView logIn;
    private Button register;
    private final UserRepository userRepository = new UserRepository();

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
        phone = ((EditText) findViewById(R.id.phoneNumber)).getText().toString();
        password = ((EditText) findViewById(R.id.password)).getText().toString();
        if(!firstName.isEmpty() && !lastName.isEmpty() && !username.isEmpty() && !phone.isEmpty() && !password.isEmpty()){
            String result = userRepository.insertUser(firstName,lastName,username,phone,password);
            if(result!=null){
                Toast.makeText(CreateAccount.this, result, Toast.LENGTH_SHORT).show();
                if(result.equalsIgnoreCase("account created.")){
                    redirectToLogIn();
                }
            }
        }else{
            Toast.makeText(CreateAccount.this, "All of fields needs to be completed!", Toast.LENGTH_SHORT).show();
        }
    }

    private void redirectToLogIn() {
        Intent registerScreen = new Intent(CreateAccount.this, LogIn.class);
        startActivity(registerScreen);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
