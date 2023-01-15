package com.example.familytasks.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task {

    @PrimaryKey(autoGenerate = true)
    private long Id;
    private String Title;
    private String Description;
    private int Priority;

    private String Status;
    private User Asignee;
    private String mDate;


    public Task(String title, String description, String date, int priority, String status, User asignee) {
        Title = title;
        Description = description;
        Priority = priority;
        Status = status;
        mDate = date;
        Asignee = asignee;
    }
    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }
    public int getPriority() {
        return Priority;
    }

    public void setPriority(int priority) {
        Priority = priority;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
    public User getAsignee() {
        return Asignee;
    }

    public void setAsignee(User asignee) {
        Asignee = asignee;
    }

}
