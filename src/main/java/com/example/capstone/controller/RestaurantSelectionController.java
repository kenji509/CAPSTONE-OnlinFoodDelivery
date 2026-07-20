package com.example.capstone.controller;

import com.example.capstone.dao.RestaurantDAO;
import com.example.capstone.model.Customer;
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
import java.util.List;

public class RestaurantSelectionController {

    @FXML private ListView<String> restaurantListView;
    @FXML private Label messageLabel;

    private final RestaurantDAO restaurantDAO = new RestaurantDAO();
    private List<Restaurant> restaurants;
    private Customer loggedInCustomer;

    public void setCustomer(Customer customer) {
        this.loggedInCustomer = customer;
    }

    @FXML
    public void initialize() {
        restaurants = restaurantDAO.getAllRestaurants();
        ObservableList<String> displayItems = FXCollections.observableArrayList();
        for (Restaurant r : restaurants) {
            displayItems.add(r.getName() + " - " + r.getRestaurantId());
        }
        restaurantListView.setItems(displayItems);
    }

    @FXML
    protected void onViewMenuClick() {
        int selectedIndex = restaurantListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            messageLabel.setText("Please select a restaurant first");
            return;
        }
        Restaurant selected = restaurants.get(selectedIndex);
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/capstone/menu-view.fxml"));
            Scene menuScene = new Scene(loader.load());
            MenuController menuController = loader.getController();
            menuController.setCustomer(loggedInCustomer);
            menuController.setRestaurantId(selected.getRestaurantId());
            Stage stage = (Stage) restaurantListView.getScene().getWindow();
            stage.setScene(menuScene);
            stage.setTitle("Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onLogoutClick() {
        SessionManager.clearSession();
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/capstone/login-view.fxml"));
            Scene loginScene = new Scene(loader.load());
            Stage stage = (Stage) restaurantListView.getScene().getWindow();
            stage.setScene(loginScene);
            stage.setTitle("Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}