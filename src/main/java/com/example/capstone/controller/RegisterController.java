package com.example.capstone.controller;

import com.example.capstone.dao.CustomerDAO;
import com.example.capstone.model.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class RegisterController {

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private TextField contactField;
    @FXML private TextField addressField;
    @FXML private Label messageLabel;

    private final CustomerDAO customerDAO = new CustomerDAO();

    @FXML
    protected void onRegisterButtonClick() {
        String name     = nameField.getText();
        String email    = emailField.getText();
        String password = passwordField.getText();
        String contact  = contactField.getText();
        String address  = addressField.getText();

        if (name.isBlank() || email.isBlank() || password.isBlank()
                || contact.isBlank() || address.isBlank()) {
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

        String userId = "C-" + System.currentTimeMillis();
        Customer newCustomer = new Customer(userId, name, email, password, contact, address);

        boolean success = customerDAO.register(newCustomer);
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
                    getClass().getResource("/com/example/capstone/login-view.fxml"));
            Scene loginScene = new Scene(loader.load());
            Stage stage = (Stage) messageLabel.getScene().getWindow();
            stage.setScene(loginScene);
            stage.setTitle("Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}