package com.example.capstone.model;

public abstract class User {
    protected String userId;
    protected String name;
    protected String email;
    protected String password;
    protected String contactNumber;

    public User(String userId, String name, String email, String password, String contactNumber) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.contactNumber = contactNumber;
    }

    public void register() {
        // TODO: persist new user (DB/file)
    }

    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }

    public void updateProfile(String name, String contactNumber) {
        this.name = name;
        this.contactNumber = contactNumber;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getContactNumber() { return contactNumber; }
}