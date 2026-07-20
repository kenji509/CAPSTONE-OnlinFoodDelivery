package com.example.capstone.controller;

import com.example.capstone.dao.OrderDAO;
import com.example.capstone.util.SessionManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class RiderDashboardController {

    @FXML private ListView<String> ordersListView;
    @FXML private Label statusLabel;

    private final OrderDAO orderDAO = new OrderDAO();

    @FXML
    public void initialize() {
        loadPendingOrders();
    }

    private void loadPendingOrders() {
        List<String> pending = orderDAO.getPendingOrders();
        ordersListView.setItems(FXCollections.observableArrayList(pending));
    }

    @FXML
    protected void onAcceptOrderClick() {
        String selected = ordersListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            String orderId  = selected.split(" - ")[0];
            boolean success = orderDAO.updateStatus(orderId, "Accepted");
            if (success) {
                statusLabel.setText("Accepted: " + orderId);
                loadPendingOrders();
            } else {
                statusLabel.setText("Failed to accept order");
            }
        } else {
            statusLabel.setText("Select an order first");
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
            Stage stage = (Stage) statusLabel.getScene().getWindow();
            stage.setScene(loginScene);
            stage.setTitle("Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}