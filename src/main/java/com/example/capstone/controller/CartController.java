package com.example.capstone.controller;

import com.example.capstone.model.*;
import com.example.capstone.service.OrderService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class CartController {
    @FXML private ListView<String> cartListView;
    @FXML private Label totalLabel;
    private List<OrderItem> cartItems;
    private Restaurant restaurant;
    private Customer customer;

    private final OrderService orderService = new OrderService();

    public void setCartData(List<OrderItem> cartItems, Restaurant restaurant, Customer customer) {
        this.cartItems = cartItems;
        this.restaurant = restaurant;
        this.customer = customer;
        ObservableList<String> displayItems = FXCollections.observableArrayList();
        double total = 0;
        for (OrderItem item : cartItems) {
            displayItems.add(item.getMenuItem().getName() + " x" + item.getQuantity()
                    + " - ₱" + item.getSubtotal());
            total += item.getSubtotal();
        }
        cartListView.setItems(displayItems);
        totalLabel.setText("Total: ₱" + total);
    }

    @FXML
    protected void onPlaceOrderClick() {
        Order order = customer.placeOrder(restaurant, cartItems);
        StringBuilder summary = new StringBuilder();
        for (OrderItem item : cartItems) {
            summary.append(item.getMenuItem().getName()).append(" x").append(item.getQuantity()).append(", ");
        }
        orderService.placeOrder(order, summary.toString());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/capstone/confirmation-view.fxml"));
            Scene confirmScene = new Scene(loader.load());
            ConfirmationController confirmController = loader.getController();
            confirmController.setOrderData(order);
            Stage stage = (Stage) totalLabel.getScene().getWindow();
            stage.setScene(confirmScene);
            stage.setTitle("Order Confirmed");
        } catch (IOException e) { e.printStackTrace(); }
    }
}