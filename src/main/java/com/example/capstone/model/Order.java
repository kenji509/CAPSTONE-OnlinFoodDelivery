package com.example.capstone.model;

import com.example.capstone.strategy.PricingStrategy;
import com.example.capstone.strategy.StandardPricingStrategy;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private String orderId;
    private LocalDateTime orderDate;
    private String status;
    private double totalAmount;
    private String deliveryAddress;

    private Customer customer;
    private Restaurant restaurant;
    private Rider rider;
    private List<OrderItem> items;
    private Payment payment;
    private PricingStrategy pricingStrategy;

    public Order(String orderId, Customer customer, Restaurant restaurant, List<OrderItem> items) {
        this.orderId         = orderId;
        this.orderDate       = LocalDateTime.now();
        this.status          = "Pending";
        this.customer        = customer;
        this.restaurant      = restaurant;
        this.items           = items;
        this.deliveryAddress = customer.getDeliveryAddress();
        this.pricingStrategy = new StandardPricingStrategy();
        this.totalAmount     = calculateTotal();
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
        this.totalAmount = calculateTotal();
    }

    public double calculateTotal() {
        this.totalAmount = pricingStrategy.calculateTotal(items);
        return totalAmount;
    }

    public void cancelOrder()              { this.status = "Cancelled"; }
    public void updateStatus(String status){ this.status = status; }
    public void setRider(Rider rider)      { this.rider = rider; }
    public void setPayment(Payment payment){ this.payment = payment; }

    public String getOrderId()          { return orderId; }
    public String getStatus()           { return status; }
    public double getTotalAmount()      { return totalAmount; }
    public Customer getCustomer()       { return customer; }
    public Restaurant getRestaurant()   { return restaurant; }
    public List<OrderItem> getItems()   { return items; }
}