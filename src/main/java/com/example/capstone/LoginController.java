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

public class LoginController {
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private CustomerDAO customerDAO = new CustomerDAO();

    @FXML
    protected void onLoginButtonClick() {
        String email = emailField.getText();
        String password = passwordField.getText();

        Customer customer = customerDAO.login(email, password);

        if (customer != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
                Scene menuScene = new Scene(loader.load());
                Stage stage = (Stage) errorLabel.getScene().getWindow();
                stage.setScene(menuScene);
                stage.setTitle("Menu");
            } catch (IOException e) {
                e.printStackTrace();
                errorLabel.setText("Error loading menu screen");
            }
        } else {
            errorLabel.setText("Invalid email or password");
        }
    }

    @FXML
    protected void onGoToRegisterClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("register-view.fxml"));
            Scene registerScene = new Scene(loader.load());
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("rider-login-view.fxml"));
            Scene riderLoginScene = new Scene(loader.load());
            Stage stage = (Stage) errorLabel.getScene().getWindow();
            stage.setScene(riderLoginScene);
            stage.setTitle("Rider Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}