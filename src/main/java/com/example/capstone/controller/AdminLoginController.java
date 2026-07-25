package com.example.capstone.controller;

import com.example.capstone.dao.AdminDAO;
import com.example.capstone.model.Admin;
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

public class AdminLoginController {

    private static final double WINDOW_WIDTH  = 400;
    private static final double WINDOW_HEIGHT = 720;

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private final AdminDAO adminDAO = new AdminDAO();

    @FXML
    protected void onLoginButtonClick() {
        String email    = emailField.getText();
        String password = passwordField.getText();

        errorLabel.setText("Logging in...");

        Task<Admin> loginTask = new Task<>() {
            @Override
            protected Admin call() {
                return adminDAO.login(email, password);
            }
        };

        loginTask.setOnSucceeded(event -> {
            Admin admin = loginTask.getValue();
            if (admin != null) {
                SessionManager.saveSession(admin);
                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("/com/example/capstone/admin-dashboard-view.fxml"));
                    Scene dashScene = new Scene(loader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
                    AdminDashboardController dashController = loader.getController();
                    dashController.setAdmin(admin);
                    Stage stage = (Stage) errorLabel.getScene().getWindow();
                    stage.setScene(dashScene);
                    stage.setTitle("Admin Dashboard");
                } catch (IOException e) {
                    e.printStackTrace();
                    errorLabel.setText("Error loading dashboard");
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
    protected void onBackToLoginClick() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/capstone/login-view.fxml"));
            Scene loginScene = new Scene(loader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
            Stage stage = (Stage) errorLabel.getScene().getWindow();
            stage.setScene(loginScene);
            stage.setTitle("Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}