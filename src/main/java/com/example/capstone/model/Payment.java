package com.example.capstone.model;

import java.time.LocalDateTime;

public class Payment {
    private String paymentId;
    private double amount;
    private String paymentMethod;
    private String paymentStatus;
    private LocalDateTime timestamp;
    private Order order;

    public Payment(String paymentId, Order order, String paymentMethod) {
        this.paymentId     = paymentId;
        this.order         = order;
        this.amount        = order.getTotalAmount();
        this.paymentMethod = paymentMethod;
        this.paymentStatus = "Pending";
        this.timestamp     = LocalDateTime.now();
    }

    public boolean processPayment() {
        this.paymentStatus = "Success";
        this.timestamp     = LocalDateTime.now();
        order.setPayment(this);
        return true;
    }

    public void refund()                 { this.paymentStatus = "Refunded"; }
    public String getPaymentStatus()     { return paymentStatus; }
    public double getAmount()            { return amount; }
}