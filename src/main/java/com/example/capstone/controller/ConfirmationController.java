package com.example.capstone.controller;

import com.example.capstone.model.Order;
import com.example.capstone.service.OrderService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class ConfirmationController {

    private static final double WINDOW_WIDTH  = 400;
    private static final double WINDOW_HEIGHT = 720;

    @FXML private Label orderIdLabel;
    @FXML private Label statusLabel;
    @FXML private Label totalLabel;
    @FXML private Label paymentLabel;
    @FXML private Label cancelMessageLabel;

    private Order order;
    private final OrderService orderService = new OrderService();

    public void setOrderData(Order order) {
        this.order = order;
        orderIdLabel.setText("Order ID: " + order.getOrderId());
        statusLabel.setText("Status: "    + order.getStatus());
        totalLabel.setText("Total: ₱"    + order.getTotalAmount());
        if (order.getPayment() != null) {
            paymentLabel.setText("Payment: " + order.getPayment().getPaymentMethod()
                    + " (" + order.getPayment().getPaymentStatus() + ")");
        }
    }

    @FXML
    protected void onCancelOrderClick() {
        boolean success = orderService.cancelOrder(order);
        if (success) {
            cancelMessageLabel.setStyle("-fx-text-fill: green;");
            cancelMessageLabel.setText("Order cancelled successfully.");
            statusLabel.setText("Status: " + order.getStatus());
            if (order.getPayment() != null) {
                order.getPayment().refund();
                paymentLabel.setText("Payment: " + order.getPayment().getPaymentMethod()
                        + " (" + order.getPayment().getPaymentStatus() + ")");
            }
        } else {
            cancelMessageLabel.setStyle("-fx-text-fill: red;");
            cancelMessageLabel.setText("Failed to cancel order.");
        }
    }

    @FXML
    protected void onRateRestaurantClick() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/capstone/review-view.fxml"));
            Scene reviewScene = new Scene(loader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
            ReviewController reviewController = loader.getController();
            reviewController.setData(order.getCustomer(), order.getRestaurant());
            Stage stage = (Stage) totalLabel.getScene().getWindow();
            stage.setScene(reviewScene);
            stage.setTitle("Rate Restaurant");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onBackToHomeClick() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/capstone/restaurant-selection-view.fxml"));
            Scene selectionScene = new Scene(loader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
            RestaurantSelectionController selectionController = loader.getController();
            selectionController.setCustomer(order.getCustomer());
            Stage stage = (Stage) totalLabel.getScene().getWindow();
            stage.setScene(selectionScene);
            stage.setTitle("Choose a Restaurant");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}