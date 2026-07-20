package com.example.capstone.controller;

import com.example.capstone.dao.RiderDAO;
import com.example.capstone.main.HelloApplication;
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

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private TextField contactField;
    @FXML private TextField vehicleField;
    @FXML private Label messageLabel;

    private final RiderDAO riderDAO = new RiderDAO();

    @FXML
    protected void onRegisterButtonClick() {
        String userId = "R-" + System.currentTimeMillis();
        Rider newRider = new Rider(
                userId,
                nameField.getText(),
                emailField.getText(),
                passwordField.getText(),
                contactField.getText(),
                vehicleField.getText(), "");
        boolean success = riderDAO.register(newRider, passwordField.getText());
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
                    HelloApplication.class.getResource("/com/example/capstone/rider-login-view.fxml"));
            Scene loginScene = new Scene(loader.load());
            Stage stage = (Stage) messageLabel.getScene().getWindow();
            stage.setScene(loginScene);
            stage.setTitle("Rider Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}