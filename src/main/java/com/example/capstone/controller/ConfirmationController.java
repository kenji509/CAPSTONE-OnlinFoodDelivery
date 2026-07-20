package com.example.capstone.controller;

import com.example.capstone.dao.OrderDAO;
import com.example.capstone.model.Order;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ConfirmationController {

    @FXML private Label orderIdLabel;
    @FXML private Label statusLabel;
    @FXML private Label totalLabel;
    @FXML private Label cancelMessageLabel;

    private Order order;
    private final OrderDAO orderDAO = new OrderDAO();

    public void setOrderData(Order order) {
        this.order = order;
        orderIdLabel.setText("Order ID: " + order.getOrderId());
        statusLabel.setText("Status: "    + order.getStatus());
        totalLabel.setText("Total: ₱"    + order.getTotalAmount());
    }

    @FXML
    protected void onCancelOrderClick() {
        boolean success = orderDAO.delete(order.getOrderId());
        if (success) {
            cancelMessageLabel.setStyle("-fx-text-fill: green;");
            cancelMessageLabel.setText("Order cancelled successfully.");
            statusLabel.setText("Status: Cancelled");
        } else {
            cancelMessageLabel.setStyle("-fx-text-fill: red;");
            cancelMessageLabel.setText("Failed to cancel order.");
        }
    }
}