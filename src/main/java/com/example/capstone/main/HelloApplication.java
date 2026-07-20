package com.example.capstone.main;

import com.example.capstone.controller.MenuController;
import com.example.capstone.model.Customer;
import com.example.capstone.model.Rider;
import com.example.capstone.model.User;
import com.example.capstone.util.SessionManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        User existingUser = SessionManager.loadSession();

        if (existingUser instanceof Customer customer) {
            FXMLLoader loader = new FXMLLoader(
                    HelloApplication.class.getResource("/com/example/capstone/menu-view.fxml"));
            Scene scene = new Scene(loader.load());
            MenuController menuController = loader.getController();
            menuController.setCustomer(customer);
            stage.setTitle("Menu");
            stage.setScene(scene);

        } else if (existingUser instanceof Rider) {
            FXMLLoader loader = new FXMLLoader(
                    HelloApplication.class.getResource("/com/example/capstone/rider-dashboard-view.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setTitle("Rider Dashboard");
            stage.setScene(scene);

        } else {
            FXMLLoader loader = new FXMLLoader(
                    HelloApplication.class.getResource("/com/example/capstone/login-view.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setTitle("Login");
            stage.setScene(scene);
        }

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}