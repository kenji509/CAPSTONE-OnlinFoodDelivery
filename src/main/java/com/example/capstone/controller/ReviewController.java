package com.example.capstone.controller;

import com.example.capstone.model.Customer;
import com.example.capstone.model.Restaurant;
import com.example.capstone.model.Review;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.io.IOException;

public class ReviewController {

    private static final double WINDOW_WIDTH  = 400;
    private static final double WINDOW_HEIGHT = 720;

    @FXML private ComboBox<Integer> ratingComboBox;
    @FXML private TextArea commentArea;
    @FXML private Label messageLabel;

    private Customer customer;
    private Restaurant restaurant;

    public void setData(Customer customer, Restaurant restaurant) {
        this.customer = customer;
        this.restaurant = restaurant;
        ratingComboBox.getItems().addAll(1, 2, 3, 4, 5);
        ratingComboBox.setValue(5);
    }

    @FXML
    protected void onSubmitReviewClick() {
        Integer rating = ratingComboBox.getValue();
        String comment = commentArea.getText();

        if (rating == null) {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("Please select a rating.");
            return;
        }

        Review review = customer.rateReview(restaurant, rating, comment);
        messageLabel.setStyle("-fx-text-fill: green;");
        messageLabel.setText("Thank you for your " + review.getRating() + "-star review!");
    }

    @FXML
    protected void onBackToHomeClick() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/capstone/restaurant-selection-view.fxml"));
            Scene selectionScene = new Scene(loader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
            RestaurantSelectionController selectionController = loader.getController();
            selectionController.setCustomer(customer);
            Stage stage = (Stage) messageLabel.getScene().getWindow();
            stage.setScene(selectionScene);
            stage.setTitle("Choose a Restaurant");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}