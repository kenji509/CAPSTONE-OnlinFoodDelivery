package com.example.capstone.controller;

import com.example.capstone.dao.CustomerDAO;
import com.example.capstone.model.Customer;
import com.example.capstone.util.SessionManager;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {

    private static final double WINDOW_WIDTH  = 400;
    private static final double WINDOW_HEIGHT = 720;

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private final CustomerDAO customerDAO = new CustomerDAO();

    @FXML
    protected void onLoginButtonClick() {
        String email    = emailField.getText();
        String password = passwordField.getText();

        errorLabel.setText("Logging in...");

        Task<Customer> loginTask = new Task<>() {
            @Override
            protected Customer call() {
                return customerDAO.login(email, password);
            }
        };

        loginTask.setOnSucceeded(event -> {
            Customer customer = loginTask.getValue();
            if (customer != null) {
                SessionManager.saveSession(customer);
                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("/com/example/capstone/restaurant-selection-view.fxml"));
                    Scene selectionScene = new Scene(loader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
                    RestaurantSelectionController selectionController = loader.getController();
                    selectionController.setCustomer(customer);
                    Stage stage = (Stage) errorLabel.getScene().getWindow();
                    stage.setScene(selectionScene);
                    stage.setTitle("Choose a Restaurant");
                } catch (IOException e) {
                    e.printStackTrace();
                    errorLabel.setText("Error loading menu screen");
                }
            } else {
                errorLabel.setText("Invalid email or password");
            }
        });

        loginTask.setOnFailed(event -> errorLabel.setText("Login failed. Please try again."));

        Thread loginThread = new Thread(loginTask);
        loginThread.setDaemon(true);
        loginThread.start();
    }

    @FXML
    protected void onGoToRegisterClick() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/capstone/register-view.fxml"));
            Scene registerScene = new Scene(loader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
            Stage stage = (Stage) errorLabel.getScene().getWindow();
            stage.setScene(registerScene);
            stage.setTitle("Register");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onGoToRiderLoginClick() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/capstone/rider-login-view.fxml"));
            Scene riderScene = new Scene(loader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
            Stage stage = (Stage) errorLabel.getScene().getWindow();
            stage.setScene(riderScene);
            stage.setTitle("Rider Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onGoToAdminLoginClick() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/capstone/admin-login-view.fxml"));
            Scene adminScene = new Scene(loader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
            Stage stage = (Stage) errorLabel.getScene().getWindow();
            stage.setScene(adminScene);
            stage.setTitle("Admin Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}