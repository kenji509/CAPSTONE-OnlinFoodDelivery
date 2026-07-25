package com.example.capstone.controller;

import com.example.capstone.dao.AdminDAO;
import com.example.capstone.dao.CustomerDAO;
import com.example.capstone.dao.RestaurantDAO;
import com.example.capstone.dao.RiderDAO;
import com.example.capstone.model.Admin;
import com.example.capstone.model.Restaurant;
import com.example.capstone.model.User;
import com.example.capstone.util.SessionManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminDashboardController {

    private static final double WINDOW_WIDTH  = 400;
    private static final double WINDOW_HEIGHT = 720;

    @FXML private TextArea outputArea;
    @FXML private Label titleLabel;

    private final AdminDAO adminDAO = new AdminDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final RiderDAO riderDAO = new RiderDAO();
    private final RestaurantDAO restaurantDAO = new RestaurantDAO();

    private Admin admin;

    public void setAdmin(Admin admin) {
        this.admin = admin;
        titleLabel.setText("🔧  Welcome, " + admin.getName());
    }

    @FXML
    protected void onViewUsersClick() {
        List<User> allUsers = new ArrayList<>();
        allUsers.addAll(customerDAO.getAllCustomers());
        allUsers.addAll(riderDAO.getAllRiders());
        outputArea.setText(admin.manageUsers(allUsers));
    }

    @FXML
    protected void onViewRestaurantsClick() {
        List<Restaurant> restaurants = restaurantDAO.getAllRestaurants();
        outputArea.setText(admin.manageRestaurants(restaurants));
    }

    @FXML
    protected void onViewReportsClick() {
        int totalOrders = adminDAO.getTotalOrderCount();
        double totalRevenue = adminDAO.getTotalRevenue();
        outputArea.setText(admin.viewReports(totalOrders, totalRevenue));
    }

    @FXML
    protected void onLogoutClick() {
        SessionManager.clearSession();
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/capstone/login-view.fxml"));
            Scene loginScene = new Scene(loader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
            Stage stage = (Stage) outputArea.getScene().getWindow();
            stage.setScene(loginScene);
            stage.setTitle("Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}