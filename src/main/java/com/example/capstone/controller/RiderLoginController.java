package com.example.capstone.controller;

import com.example.capstone.dao.RiderDAO;
import com.example.capstone.model.Rider;
import com.example.capstone.util.SessionManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class RiderLoginController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private final RiderDAO riderDAO = new RiderDAO();

    @FXML
    protected void onLoginButtonClick() {
        String email    = emailField.getText();
        String password = passwordField.getText();
        Rider rider = riderDAO.login(email, password);
        if (rider != null) {
            // SRP: SessionManager handles session, RiderLoginController handles navigation
            SessionManager.saveSession(rider);
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/com/example/capstone/rider-dashboard-view.fxml"));
                Scene dashScene = new Scene(loader.load());
                Stage stage = (Stage) errorLabel.getScene().getWindow();
                stage.setScene(dashScene);
                stage.setTitle("Rider Dashboard");
            } catch (IOException e) {
                e.printStackTrace();
                errorLabel.setText("Error loading dashboard");
            }
        } else {
            errorLabel.setText("Invalid email or password");
        }
    }

    @FXML
    protected void onGoToRegisterClick() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/capstone/rider-register-view.fxml"));
            Scene registerScene = new Scene(loader.load());
            Stage stage = (Stage) errorLabel.getScene().getWindow();
            stage.setScene(registerScene);
            stage.setTitle("Rider Register");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}