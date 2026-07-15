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

    // Hardcoded test customer for today - no database yet
    private Customer testCustomer = new Customer("C1", "Kenji", "kenji@email.com",
            "pass123", "0917xxxxxxx", "Dalaguete, Cebu");

    @FXML
    protected void onLoginButtonClick() {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (testCustomer.login(email, password)) {
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
}