package com.example.familytasks.model;

import com.example.familytasks.R;

public class InProgressStatus implements Status{
    @Override
    public String getStatusName() {
        return "In progress";
    }

    @Override
    public String getColor() {
        return "#0066ff";
    }

    @Override
    public int getBackground() {
        return R.drawable.status_in_progress_background;
    }
}
