package com.example.familytasks.enums;

public enum GmailAccount {
    Email("happyhappyfamilytasks@gmail.com"),
    Password("rmuvxkeviuflajtn");

    private String value;

    GmailAccount(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
