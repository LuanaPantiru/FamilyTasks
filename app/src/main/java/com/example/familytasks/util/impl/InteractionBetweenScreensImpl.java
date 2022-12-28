package com.example.familytasks.util.impl;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.familytasks.CreateAccount;
import com.example.familytasks.LogIn;
import com.example.familytasks.util.InteractionsBetweenScreens;

public class InteractionBetweenScreensImpl implements InteractionsBetweenScreens {
    @Override
    public void changeScreen(AppCompatActivity context, Intent intent) {
        context.startActivity(intent);
        context.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
