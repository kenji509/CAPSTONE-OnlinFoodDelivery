package com.example.capstone.controller;

import com.example.capstone.model.Customer;
import com.example.capstone.model.Order;
import com.example.capstone.model.Rider;
import com.example.capstone.service.OrderService;
import com.example.capstone.util.SessionManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RiderDashboardController {

    private static final double WINDOW_WIDTH  = 400;
    private static final double WINDOW_HEIGHT = 720;

    @FXML private ListView<String> ordersListView;
    @FXML private Label statusLabel;

    private final OrderService orderService = new OrderService();
    private Rider loggedInRider;

    public void setRider(Rider rider) {
        this.loggedInRider = rider;
    }

    @FXML
    public void initialize() {
        ordersListView.setCellFactory(list -> new ListCell<>() {
            private final Label label = new Label();
            {
                label.setWrapText(true);
                label.maxWidthProperty().bind(list.widthProperty().subtract(40));
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    label.setText(item);
                    setGraphic(label);
                    setText(null);
                }
            }
        });
        loadPendingOrders();
    }

    private void loadPendingOrders() {
        List<String> pending = orderService.getPendingOrders();
        ordersListView.setItems(FXCollections.observableArrayList(pending));
    }

    @FXML
    protected void onAcceptOrderClick() {
        String selected = ordersListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            String orderId  = selected.split(" - ")[0];
            boolean success = orderService.acceptOrder(orderId, loggedInRider.getUserId());
            if (success) {
                Customer placeholderCustomer = new Customer("", "", "", "", "", "");
                Order placeholderOrder = new Order(orderId, placeholderCustomer, null, new ArrayList<>());
                loggedInRider.acceptDelivery(placeholderOrder);

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
        SessionManager.clearSession();
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/capstone/login-view.fxml"));
            Scene loginScene = new Scene(loader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
            Stage stage = (Stage) statusLabel.getScene().getWindow();
            stage.setScene(loginScene);
            stage.setTitle("Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}