package com.example.familytasks.util;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public interface InteractionsBetweenScreens {
    void changeScreen(AppCompatActivity context, Intent intent);
}
