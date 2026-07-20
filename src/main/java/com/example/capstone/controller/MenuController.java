package com.example.capstone.controller;

import com.example.capstone.dao.RestaurantDAO;
import com.example.capstone.model.Customer;
import com.example.capstone.model.MenuItem;
import com.example.capstone.model.OrderItem;
import com.example.capstone.model.Restaurant;
import com.example.capstone.util.SessionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuController {

    @FXML private ListView<String> menuListView;
    @FXML private Label cartLabel;

    private Restaurant restaurant;
    private List<MenuItem> menuItems;
    private List<OrderItem> cartItems = new ArrayList<>();
    private Customer loggedInCustomer;

    public void setCustomer(Customer customer) {
        this.loggedInCustomer = customer;
    }

    @FXML
    public void initialize() {
        RestaurantDAO restaurantDAO = new RestaurantDAO();
        restaurant = restaurantDAO.getRestaurantWithMenu("R1");
        menuItems  = restaurant.getMenu();
        ObservableList<String> displayItems = FXCollections.observableArrayList();
        for (MenuItem item : menuItems) {
            displayItems.add(item.getName() + " - ₱" + item.getPrice());
        }
        menuListView.setItems(displayItems);
    }

    @FXML
    protected void onAddToCartClick() {
        int selectedIndex = menuListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            MenuItem selected = menuItems.get(selectedIndex);
            cartItems.add(new OrderItem(selected, 1, ""));
            cartLabel.setText("Cart: " + cartItems.size() + " items");
        }
    }

    @FXML
    protected void onCheckoutClick() {
        if (cartItems.isEmpty()) {
            cartLabel.setText("Cart is empty - add items first");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/capstone/cart-view.fxml"));
            Scene cartScene = new Scene(loader.load());
            CartController cartController = loader.getController();
            cartController.setCartData(cartItems, restaurant, loggedInCustomer);
            Stage stage = (Stage) cartLabel.getScene().getWindow();
            stage.setScene(cartScene);
            stage.setTitle("Cart");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onLogoutClick() {
        // SRP: SessionManager handles session deletion
        SessionManager.clearSession();
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/capstone/login-view.fxml"));
            Scene loginScene = new Scene(loader.load());
            Stage stage = (Stage) cartLabel.getScene().getWindow();
            stage.setScene(loginScene);
            stage.setTitle("Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}