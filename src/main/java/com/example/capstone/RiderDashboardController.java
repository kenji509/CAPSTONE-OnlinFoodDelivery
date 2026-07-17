package com.example.capstone;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class RiderDashboardController {
    @FXML private ListView<String> ordersListView;
    @FXML private Label statusLabel;

    @FXML
    public void initialize() {
        ordersListView.setItems(FXCollections.observableArrayList(
                "Order #1 - Jollibee - ₱233.00",
                "Order #2 - McDonald's - ₱150.00"
        ));
    }

    @FXML
    protected void onAcceptOrderClick() {
        String selected = ordersListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            statusLabel.setText("Accepted: " + selected);
        } else {
            statusLabel.setText("Select an order first");
        }
    }
}