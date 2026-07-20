package com.example.capstone.controller;

import com.example.capstone.dao.OrderDAO;
import com.example.capstone.model.Customer;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class OrderHistoryController {

    @FXML private ListView<String> historyListView;
    @FXML private Label emptyLabel;

    private final OrderDAO orderDAO = new OrderDAO();
    private Customer customer;

    public void setCustomer(Customer customer) {
        this.customer = customer;
        loadHistory();
    }

    private void loadHistory() {
        List<String> history = orderDAO.getOrderHistory(customer.getUserId());
        if (history.isEmpty()) {
            emptyLabel.setText("You have no past orders yet.");
        } else {
            emptyLabel.setText("");
        }
        historyListView.setItems(FXCollections.observableArrayList(history));
    }

    @FXML
    protected void onBackToHomeClick() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/capstone/restaurant-selection-view.fxml"));
            Scene selectionScene = new Scene(loader.load());
            RestaurantSelectionController selectionController = loader.getController();
            selectionController.setCustomer(customer);
            Stage stage = (Stage) historyListView.getScene().getWindow();
            stage.setScene(selectionScene);
            stage.setTitle("Choose a Restaurant");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}