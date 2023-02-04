package com.example.familytasks.model;

import com.example.familytasks.R;

public class ToDoStatus implements Status{
    @Override
    public String getStatusName() {
        return "To do";
    }

    @Override
    public String getColor() {
        return "#00cc00";
    }

    @Override
    public int getBackground() {
        return R.drawable.status_to_do_background;
    }
}
