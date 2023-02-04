package com.example.familytasks;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.familytasks.model.User;
import com.example.familytasks.repository.UserRepository;
import com.example.familytasks.util.InteractionsBetweenScreens;
import com.example.familytasks.util.MailApi;
import com.example.familytasks.util.impl.InteractionBetweenScreensImpl;

public class ResetPassword extends AppCompatActivity {
    private String email;
    private Button send;
    private final UserRepository userRepository = new UserRepository();
    private final InteractionsBetweenScreens interactionsBetweenScreens = new InteractionBetweenScreensImpl();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);
        sendSMS();
    }

    private void sendSMS() {
        send = findViewById(R.id.sendEmail);
        send.setOnClickListener(view -> {
            email = ((EditText) findViewById(R.id.email)).getText().toString();
            if(!email.isEmpty()){
                if(email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
                    User user = userRepository.findUserByEmail(email);
                    if(user!=null){
                        int code = (int)Math.floor(Math.random()*(9999-1000+1)+1000);
                        MailApi mailApi = new MailApi(ResetPassword.this,email,"Reset Password", String.valueOf(code));
                        mailApi.execute();
                        Intent registerScreen = new Intent(ResetPassword.this, CheckCode.class);
                        registerScreen.putExtra("code",code);
                        registerScreen.putExtra("email",email);
                        interactionsBetweenScreens.changeScreen(ResetPassword.this,registerScreen);
                    }else{
                        Toast.makeText(getApplicationContext(),"Doesn't exist an account associated with this email address!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ResetPassword.this, "Email doesn't have the correct pattern.", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getApplicationContext(),"Enter the email address!", Toast.LENGTH_SHORT).show();
            }

        });

    }

}
