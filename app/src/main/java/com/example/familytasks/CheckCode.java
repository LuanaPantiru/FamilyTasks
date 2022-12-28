package com.example.familytasks;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.familytasks.util.InteractionsBetweenScreens;
import com.example.familytasks.util.impl.InteractionBetweenScreensImpl;

public class CheckCode extends AppCompatActivity {
    private TextView inputCode;
    private int correctCode;
    private final InteractionsBetweenScreens interactionsBetweenScreens = new InteractionBetweenScreensImpl();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.code_verification);
        correctCode = getIntent().getExtras().getInt("code");
        inputCode = findViewById(R.id.code);
        inputCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void afterTextChanged(Editable editable) {
                String code = inputCode.getText().toString();
                if(code.length() == 4){
                    Button check = findViewById(R.id.check);
                    check.setClickable(true);
                    check.setBackground(getApplicationContext().getDrawable(R.drawable.button_active));
                    check.setOnClickListener(view -> {
                        if(correctCode == Integer.parseInt(code)){
                            Intent registerScreen = new Intent(CheckCode.this, NewPassword.class);
                            registerScreen.putExtra("email",getIntent().getExtras().getString("email"));
                            interactionsBetweenScreens.changeScreen(CheckCode.this,registerScreen);
                        }
                    });
                }
            }
        });
    }

}
