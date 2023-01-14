package com.example.familytasks.model.enums;

public enum Activities {
    All_members("All members"),
    My_tasks("My tasks"),
    All_to_do_tasks("All to do tasks"),
    All_in_progress_task("All in progress task"),
    All_finish_task("All finish tasks");

    private String activityName;

    Activities(String activitiName) {
        this.activityName = activitiName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
}
