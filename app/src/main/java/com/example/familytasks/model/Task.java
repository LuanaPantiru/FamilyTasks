package com.example.familytasks.model;


import static androidx.room.ForeignKey.NO_ACTION;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(foreignKeys = {@ForeignKey(entity = User.class,
        parentColumns = "id",
        childColumns = "idUser",
        onDelete = ForeignKey.NO_ACTION),
        @ForeignKey(entity = FamilyGroup.class,
                parentColumns = "id",
                childColumns = "idFamilyGroup",
                onDelete = NO_ACTION)
}, indices = {@Index("idUser"), @Index(("idFamilyGroup"))})
public class Task implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String title;
    private String description;
    private String priority;
    private String status;

    private long idUser;

    private long idFamilyGroup;
    @Ignore
    protected Status statusProp;


    public Task(String title, String description, String priority, String status, long idUser, long idFamilyGroup) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.idUser = idUser;
        this.idFamilyGroup = idFamilyGroup;
        if (status.equals("To do")) {
            statusProp = new ToDoStatus();
        } else if (status.equals("In progress")) {
            statusProp = new InProgressStatus();
        } else {
            statusProp = new FinishedStatus();
        }
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


    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
        if (status.equals("To do")) {
            statusProp = new ToDoStatus();
        } else if (status.equals("In progress")) {
            statusProp = new InProgressStatus();
        } else {
            statusProp = new FinishedStatus();
        }
    }

    public long getIdUser() {
        return this.idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public long getIdFamilyGroup() {
        return this.idFamilyGroup;
    }

    public void setIdFamilyGroup(long idFamilyGroup) {
        this.idFamilyGroup = idFamilyGroup;
    }

    public String getTextColor(){
        return statusProp.getColor();
    }

    public int getBackground(){
        return statusProp.getBackground();
    }

}
