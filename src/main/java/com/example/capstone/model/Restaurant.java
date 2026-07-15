package com.example.capstone.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String restaurantId;
    private String name;
    private String address;
    private double rating;
    private boolean isOpen;
    private List<MenuItem> menu = new ArrayList<>();

    public Restaurant(String restaurantId, String name, String address) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.address = address;
        this.rating = 0.0;
        this.isOpen = true;
    }

    public void addMenuItem(MenuItem item) {
        menu.add(item);
    }

    public void updateMenu(MenuItem item, double newPrice, boolean isAvailable) {
        item.setPrice(newPrice);
        item.setAvailable(isAvailable);
    }

    public boolean acceptOrder(Order order) {
        order.updateStatus("Accepted");
        return true;
    }

    public boolean rejectOrder(Order order) {
        order.updateStatus("Rejected");
        return false;
    }

    public String getRestaurantId() { return restaurantId; }
    public String getName() { return name; }
    public double getRating() { return rating; }
    public boolean isOpen() { return isOpen; }
    public List<MenuItem> getMenu() { return menu; }
}