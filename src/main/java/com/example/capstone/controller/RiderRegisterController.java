package com.example.capstone.controller;

import com.example.capstone.dao.RiderDAO;
import com.example.capstone.model.Rider;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class RiderRegisterController {

    private static final double WINDOW_WIDTH  = 400;
    private static final double WINDOW_HEIGHT = 720;

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private TextField contactField;
    @FXML private TextField vehicleField;
    @FXML private Label messageLabel;

    private final RiderDAO riderDAO = new RiderDAO();

    @FXML
    protected void onRegisterButtonClick() {
        String name     = nameField.getText();
        String email    = emailField.getText();
        String password = passwordField.getText();
        String contact  = contactField.getText();
        String vehicle  = vehicleField.getText();

        if (name.isBlank() || email.isBlank() || password.isBlank()
                || contact.isBlank() || vehicle.isBlank()) {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("All fields are required.");
            return;
        }

        if (!email.matches("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("Please enter a valid email address.");
            return;
        }

        if (password.length() < 6) {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("Password must be at least 6 characters.");
            return;
        }

        if (riderDAO.emailExists(email)) {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("This email is already registered.");
            return;
        }

        String userId = "R-" + System.currentTimeMillis();
        Rider newRider = new Rider(userId, name, email, password, contact, vehicle, "");

        boolean success = riderDAO.register(newRider, password);
        if (success) {
            messageLabel.setStyle("-fx-text-fill: green;");
            messageLabel.setText("Registered! You can now login.");
        } else {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("Registration failed. Try again.");
        }
    }

    @FXML
    protected void onGoToLoginClick() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/capstone/rider-login-view.fxml"));
            Scene loginScene = new Scene(loader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
            Stage stage = (Stage) messageLabel.getScene().getWindow();
            stage.setScene(loginScene);
            stage.setTitle("Rider Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}