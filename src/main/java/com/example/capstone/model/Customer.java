package com.example.capstone.model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private String deliveryAddress;
    private int loyaltyPoints;
    private List<Order> orderHistory = new ArrayList<>();
    private List<Review> reviews = new ArrayList<>();

    public Customer(String userId, String name, String email, String password,
                    String contactNumber, String deliveryAddress) {
        super(userId, name, email, password, contactNumber);
        this.deliveryAddress = deliveryAddress;
        this.loyaltyPoints = 0;
    }

    public List<Restaurant> browseRestaurants(List<Restaurant> allRestaurants) {
        return allRestaurants;
    }

    public Order placeOrder(Restaurant restaurant, List<OrderItem> items) {
        Order order = new Order("ORD-" + System.currentTimeMillis(), this, restaurant, items);
        orderHistory.add(order);
        return order;
    }

    public void trackOrder(Order order) {
        // TODO: fetch/display order.getStatus()
    }

    public Review rateReview(Restaurant restaurant, int rating, String comment) {
        Review review = new Review("REV-" + System.currentTimeMillis(), rating, comment);
        reviews.add(review);
        return review;
    }



    public String getDeliveryAddress() { return deliveryAddress; }
    public int getLoyaltyPoints() { return loyaltyPoints; }
    public List<Order> getOrderHistory() { return orderHistory; }

    public String getPassword() {
        return password;
    }
}