package com.example.capstone.model;

import java.util.List;

public class Admin extends User {
    private String accessLevel;

    public Admin(String userId, String name, String email, String password,
                 String contactNumber, String accessLevel) {
        super(userId, name, email, password, contactNumber);
        this.accessLevel = accessLevel;
    }

    public void manageUsers(List<User> users) {}
    public void manageRestaurants(List<Restaurant> restaurants) {}
    public void viewReports(List<Order> allOrders) {}

    public String getAccessLevel() { return accessLevel; }
}