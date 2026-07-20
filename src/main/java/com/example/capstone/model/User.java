package com.example.capstone.model;

import java.io.Serializable;

// DIP: User is the base abstraction all user types depend on
public abstract class User implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String userId;
    protected String name;
    protected String email;
    protected String password;
    protected String contactNumber;

    public User(String userId, String name, String email, String password, String contactNumber) {
        this.userId        = userId;
        this.name          = name;
        this.email         = email;
        this.password      = password;
        this.contactNumber = contactNumber;
    }

    public void register() {}

    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }

    public void updateProfile(String name, String contactNumber) {
        this.name          = name;
        this.contactNumber = contactNumber;
    }

    public String getUserId()        { return userId; }
    public String getName()          { return name; }
    public String getEmail()         { return email; }
    public String getContactNumber() { return contactNumber; }
}