package com.example.capstone.main;

import com.example.capstone.controller.AdminDashboardController;
import com.example.capstone.controller.RestaurantSelectionController;
import com.example.capstone.model.Admin;
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

    private static final double WINDOW_WIDTH  = 400;
    private static final double WINDOW_HEIGHT  = 720;

    @Override
    public void start(Stage stage) throws IOException {

        User existingUser = SessionManager.loadSession();

        if (existingUser instanceof Customer customer) {
            FXMLLoader loader = new FXMLLoader(
                    HelloApplication.class.getResource("/com/example/capstone/restaurant-selection-view.fxml"));
            Scene scene = new Scene(loader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
            RestaurantSelectionController selectionController = loader.getController();
            selectionController.setCustomer(customer);
            stage.setTitle("Choose a Restaurant");
            stage.setScene(scene);

        } else if (existingUser instanceof Rider) {
            FXMLLoader loader = new FXMLLoader(
                    HelloApplication.class.getResource("/com/example/capstone/rider-dashboard-view.fxml"));
            Scene scene = new Scene(loader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
            stage.setTitle("Rider Dashboard");
            stage.setScene(scene);

        } else if (existingUser instanceof Admin admin) {
            FXMLLoader loader = new FXMLLoader(
                    HelloApplication.class.getResource("/com/example/capstone/admin-dashboard-view.fxml"));
            Scene scene = new Scene(loader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
            AdminDashboardController dashController = loader.getController();
            dashController.setAdmin(admin);
            stage.setTitle("Admin Dashboard");
            stage.setScene(scene);

        } else {
            FXMLLoader loader = new FXMLLoader(
                    HelloApplication.class.getResource("/com/example/capstone/login-view.fxml"));
            Scene scene = new Scene(loader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
            stage.setTitle("Login");
            stage.setScene(scene);
        }

        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}