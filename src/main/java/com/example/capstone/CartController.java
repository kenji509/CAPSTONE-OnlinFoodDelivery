package com.example.capstone;

import com.example.capstone.model.*;
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

    public void setCartData(List<OrderItem> cartItems, Restaurant restaurant) {
        this.cartItems = cartItems;
        this.restaurant = restaurant;

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
        Customer testCustomer = new Customer("C1", "Kenji", "kenji@email.com",
                "pass123", "0917xxxxxxx", "Dalaguete, Cebu");

        Order order = testCustomer.placeOrder(restaurant, cartItems);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("confirmation-view.fxml"));
            Scene confirmScene = new Scene(loader.load());

            ConfirmationController confirmController = loader.getController();
            confirmController.setOrderData(order);

            Stage stage = (Stage) totalLabel.getScene().getWindow();
            stage.setScene(confirmScene);
            stage.setTitle("Order Confirmed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}