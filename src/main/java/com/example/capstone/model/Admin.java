package com.example.capstone.model;

import java.io.Serializable;
import java.util.List;

public class Admin extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String accessLevel;

    public Admin(String userId, String name, String email, String password,
                 String contactNumber, String accessLevel) {
        super(userId, name, email, password, contactNumber);
        this.accessLevel = accessLevel;
    }

    public String manageUsers(List<User> users) {
        StringBuilder sb = new StringBuilder();
        sb.append("Total Users: ").append(users.size()).append("\n\n");
        for (User u : users) {
            sb.append("- ").append(u.getName()).append(" (").append(u.getEmail()).append(")\n");
        }
        return sb.toString();
    }

    public String manageRestaurants(List<Restaurant> restaurants) {
        StringBuilder sb = new StringBuilder();
        sb.append("Total Restaurants: ").append(restaurants.size()).append("\n\n");
        for (Restaurant r : restaurants) {
            sb.append("- ").append(r.getName()).append(" (").append(r.getAddress()).append(")\n");
        }
        return sb.toString();
    }

    public String viewReports(int totalOrders, double totalRevenue) {
        return "Total Orders: " + totalOrders + "\nTotal Revenue: ₱" + totalRevenue;
    }

    public String getAccessLevel() { return accessLevel; }
}