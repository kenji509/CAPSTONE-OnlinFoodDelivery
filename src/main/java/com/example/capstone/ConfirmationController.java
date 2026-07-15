package com.example.capstone;

import com.example.capstone.model.Order;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ConfirmationController {
    @FXML private Label orderIdLabel;
    @FXML private Label statusLabel;
    @FXML private Label totalLabel;

    public void setOrderData(Order order) {
        orderIdLabel.setText("Order ID: " + order.getOrderId());
        statusLabel.setText("Status: " + order.getStatus());
        totalLabel.setText("Total: ₱" + order.getTotalAmount());
    }
}