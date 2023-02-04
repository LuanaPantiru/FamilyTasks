package com.example.familytasks.model;

import com.example.familytasks.R;

public class FinishedStatus implements Status{

    @Override
    public String getColor() {
        return "#ff6600";
    }

    @Override
    public int getBackground() {
        return R.drawable.status_finished_background;
    }
}
