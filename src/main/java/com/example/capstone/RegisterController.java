package com.example.capstone;

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

    private CustomerDAO customerDAO = new CustomerDAO();

    @FXML
    protected void onRegisterButtonClick() {
        String userId = "C-" + System.currentTimeMillis();
        Customer newCustomer = new Customer(userId, nameField.getText(), emailField.getText(),
                passwordField.getText(), contactField.getText(), addressField.getText());

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Scene loginScene = new Scene(loader.load());
            Stage stage = (Stage) messageLabel.getScene().getWindow();
            stage.setScene(loginScene);
            stage.setTitle("Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}