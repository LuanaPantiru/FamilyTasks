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
import com.example.familytasks.util.InteractionsBetweenScreens;
import com.example.familytasks.util.MailApi;
import com.example.familytasks.util.impl.InteractionBetweenScreensImpl;

import lombok.ToString;

public class LogIn extends AppCompatActivity{
    private String email;
    private String password;
    private Boolean rememberMe;
    private Button logIn;
    private int numberOfLogIn = 0;

    private User user;
    private final UserRepository userRepository = new UserRepository();
    private final InteractionsBetweenScreens interactionsBetweenScreens = new InteractionBetweenScreensImpl();
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        checkRemember();
        TextView createAccount = findViewById(R.id.redirectCreateAccount);
        TextView forgotPassword = findViewById(R.id.redirectForgotPassword);
        logIn = findViewById(R.id.logIn);

        createAccount.setOnClickListener(view -> {
            Intent registerScreen = new Intent(LogIn.this, CreateAccount.class);
            interactionsBetweenScreens.changeScreen(LogIn.this,registerScreen);
        });

        forgotPassword.setOnClickListener(view -> {
            Intent registerScreen = new Intent(LogIn.this, ResetPassword.class);
            interactionsBetweenScreens.changeScreen(LogIn.this,registerScreen);
        });

        logIn.setOnClickListener(view -> {
            if(numberOfLogIn<3){
                if(validateAccount()){
                    Toast.makeText(LogIn.this, "Log in with success", Toast.LENGTH_SHORT).show();
                    Intent registerScreen = new Intent(LogIn.this, MainActivity.class);
                    registerScreen.putExtra("id", user.getId());
                    interactionsBetweenScreens.changeScreen(LogIn.this,registerScreen);
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
            Long value = Long.parseLong(sharedPreferences.getString(key,null));
            Intent registerScreen = new Intent(LogIn.this, MainActivity.class);
            registerScreen.putExtra("id",value);
            interactionsBetweenScreens.changeScreen(LogIn.this,registerScreen);
        }
    }

    private boolean validateAccount(){
        email = ((EditText) findViewById(R.id.email)).getText().toString();
        password = ((EditText) findViewById(R.id.password)).getText().toString();
        if(!email.isEmpty() && !password.isEmpty()){
            if(email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
                user = userRepository.findUserByEmail(email);
                if (user != null) {
                    if(!user.getActive()){
                        Toast.makeText(LogIn.this, "Account is not active. Reset password!", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    if (user.getPassword().equals(password)) {
                        rememberMe = ((CheckBox) findViewById(R.id.rememberMe)).isChecked();
                        doRememberMe(user);
                        return true;
                    } else {
                        numberOfLogIn++;
                        Toast.makeText(LogIn.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                } else {
                    Toast.makeText(LogIn.this, "This email address is not register.", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else{
                Toast.makeText(LogIn.this, "Email doesn't have the correct pattern.", Toast.LENGTH_SHORT).show();
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
            editor.putString(user.getEmail()+"rememberMe",String.valueOf(user.getId()));
            editor.apply();
        }
    }

    private void lockAccount(){
        userRepository.deactivateAccount(email);
        int code = (int)Math.floor(Math.random()*(9999-1000+1)+1000);

        MailApi mailApi = new MailApi(LogIn.this,email,"Reset Password", String.valueOf(code));
        mailApi.execute();
        Intent registerScreen = new Intent(LogIn.this, CheckCode.class);
        registerScreen.putExtra("code",code);
        registerScreen.putExtra("email",email);
        interactionsBetweenScreens.changeScreen(LogIn.this,registerScreen);
    }

}