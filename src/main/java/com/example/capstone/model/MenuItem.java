package com.example.capstone.model;

public class MenuItem {
    private String itemId;
    private String name;
    private String description;
    private double price;
    private String category;
    private boolean isAvailable;

    public MenuItem(String itemId, String name, String description, double price, String category) {
        this.itemId      = itemId;
        this.name        = name;
        this.description = description;
        this.price       = price;
        this.category    = category;
        this.isAvailable = true;
    }

    public String getItemId()    { return itemId; }
    public String getName()      { return name; }
    public double getPrice()     { return price; }
    public boolean isAvailable() { return isAvailable; }

    public void setPrice(double price)          { this.price = price; }
    public void setAvailable(boolean available) { this.isAvailable = available; }
}