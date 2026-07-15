package com.example.capstone.model;

public class OrderItem {
    private MenuItem menuItem;
    private int quantity;
    private String specialInstructions;

    public OrderItem(MenuItem menuItem, int quantity, String specialInstructions) {
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.specialInstructions = specialInstructions;
    }

    public double getSubtotal() {
        return menuItem.getPrice() * quantity;
    }

    public MenuItem getMenuItem() { return menuItem; }
    public int getQuantity() { return quantity; }
}