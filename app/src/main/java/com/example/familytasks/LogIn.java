package com.example.familytasks;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.familytasks.model.User;
import com.example.familytasks.repository.UserRepository;

public class LogIn extends AppCompatActivity{
    private String phone;
    private String password;
    private Boolean rememberMe;
    private Button logIn;
    private int numberOfLogIn = 0;
    private final UserRepository userRepository = new UserRepository();
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        checkRemember();
        TextView createAccount = findViewById(R.id.redirectCreateAccount);
        logIn = findViewById(R.id.logIn);

        createAccount.setOnClickListener(view -> {
            Intent registerScreen = new Intent(LogIn.this, CreateAccount.class);
            startActivity(registerScreen);
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        });

        logIn.setOnClickListener(view -> {
            if(numberOfLogIn<3){
                if(validateAccount()){
                    Toast.makeText(LogIn.this, "Log in with success", Toast.LENGTH_SHORT).show();
                    Intent registerScreen = new Intent(LogIn.this, MainActivity.class);
                    registerScreen.putExtra("phone",phone);
                    startActivity(registerScreen);
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                }
            }else{
                lockAccount();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void checkRemember() {
        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", MODE_PRIVATE);
        String key = sharedPreferences.getAll().keySet().stream().filter(sp -> sp.contains("rememberMe")).findAny().orElse(null);
        if(key!=null){
            String value = sharedPreferences.getString(key,null);
            if(value!=null){
               Intent registerScreen = new Intent(LogIn.this, MainActivity.class);
               registerScreen.putExtra("phone",value);
               startActivity(registerScreen);
               overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        }
    }

    private boolean validateAccount(){
        phone = ((EditText) findViewById(R.id.phoneNumber)).getText().toString();
        password = ((EditText) findViewById(R.id.password)).getText().toString();
        if(!phone.isEmpty() && !password.isEmpty()){
            User user = userRepository.findUserByPhoneNumber(phone);
            if(user!=null){
                if(user.getPassword().equals(password)){
                    rememberMe = ((CheckBox) findViewById(R.id.rememberMe)).isChecked();
                    doRememberMe(user);
                    return true;
                }else{
                    numberOfLogIn++;
                    Toast.makeText(LogIn.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else{
                Toast.makeText(LogIn.this, "This phone number is not register.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            Toast.makeText(LogIn.this, "All fields need to be completed", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void doRememberMe(User user) {
        if(rememberMe){
            SharedPreferences preferences = getSharedPreferences("PREFS", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(user.getUserName()+"rememberMe",user.getPhoneNumber());
            editor.apply();
        }
    }

    private void lockAccount(){
        Intent registerScreen = new Intent(LogIn.this, ResetPassword.class);
        startActivity(registerScreen);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

}