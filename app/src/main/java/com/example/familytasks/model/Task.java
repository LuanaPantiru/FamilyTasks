package com.example.familytasks.model;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Task {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String title;
    private String description;
    private int priority;

    private String status;
    @Ignore
    private User assignee;


    public Task(String title, String description, int priority, String status) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        //this.assignee = assignee;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public User getAssignee() {
        return this.assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

}
