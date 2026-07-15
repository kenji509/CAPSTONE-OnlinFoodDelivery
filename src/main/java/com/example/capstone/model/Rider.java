package com.example.capstone.model;

public class Rider extends User {
    private String vehicleType;
    private String licenseNumber;
    private boolean isAvailable;

    public Rider(String userId, String name, String email, String password,
                 String contactNumber, String vehicleType, String licenseNumber) {
        super(userId, name, email, password, contactNumber);
        this.vehicleType = vehicleType;
        this.licenseNumber = licenseNumber;
        this.isAvailable = true;
    }

    public void acceptDelivery(Order order) {
        order.setRider(this);
        this.isAvailable = false;
    }

    public void updateStatus(Order order, String status) {
        order.updateStatus(status);
        if (status.equalsIgnoreCase("Delivered")) {
            this.isAvailable = true;
        }
    }

    public boolean isAvailable() { return isAvailable; }
    public String getVehicleType() { return vehicleType; }
}