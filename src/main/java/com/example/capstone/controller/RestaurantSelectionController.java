package com.example.capstone.controller;

import com.example.capstone.dao.RestaurantDAO;
import com.example.capstone.model.Customer;
import com.example.capstone.model.Restaurant;
import com.example.capstone.util.SessionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class RestaurantSelectionController {

    private static final double WINDOW_WIDTH  = 400;
    private static final double WINDOW_HEIGHT = 720;

    @FXML private ListView<Restaurant> restaurantListView;
    @FXML private Label messageLabel;

    private final RestaurantDAO restaurantDAO = new RestaurantDAO();
    private Customer loggedInCustomer;

    public void setCustomer(Customer customer) {
        this.loggedInCustomer = customer;
    }

    @FXML
    public void initialize() {
        List<Restaurant> restaurants = restaurantDAO.getAllRestaurants();
        ObservableList<Restaurant> items = FXCollections.observableArrayList(restaurants);
        restaurantListView.setItems(items);

        restaurantListView.setCellFactory(list -> new ListCell<>() {
            @Override
            protected void updateItem(Restaurant restaurant, boolean empty) {
                super.updateItem(restaurant, empty);
                if (empty || restaurant == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    ImageView logo = new ImageView(loadLogo(restaurant.getRestaurantId()));
                    logo.setFitWidth(50);
                    logo.setFitHeight(50);
                    logo.setPreserveRatio(true);

                    Label name = new Label(restaurant.getName());
                    name.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

                    Label address = new Label(restaurant.getAddress());
                    address.setStyle("-fx-font-size: 11px; -fx-text-fill: #888888;");

                    VBox textBox = new VBox(2, name, address);
                    HBox row = new HBox(12, logo, textBox);
                    row.setAlignment(Pos.CENTER_LEFT);
                    row.setPadding(new Insets(2));

                    setGraphic(row);
                    setText(null);
                }
            }
        });
    }

    private Image loadLogo(String restaurantId) {
        String filename = switch (restaurantId) {
            case "R1" -> "Jollibee.png";
            case "R2" -> "Mag_inasal.png";
            case "R3" -> "Chowking.png";
            default -> null;
        };
        if (filename == null) return null;
        return new Image(getClass().getResourceAsStream("/com/example/capstone/images/" + filename));
    }

    @FXML
    protected void onViewMenuClick() {
        Restaurant selected = restaurantListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            messageLabel.setText("Please select a restaurant first");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/capstone/menu-view.fxml"));
            Scene menuScene = new Scene(loader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
            MenuController menuController = loader.getController();
            menuController.setCustomer(loggedInCustomer);
            menuController.setRestaurantId(selected.getRestaurantId());
            Stage stage = (Stage) restaurantListView.getScene().getWindow();
            stage.setScene(menuScene);
            stage.setTitle("Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onLogoutClick() {
        SessionManager.clearSession();
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/capstone/login-view.fxml"));
            Scene loginScene = new Scene(loader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
            Stage stage = (Stage) restaurantListView.getScene().getWindow();
            stage.setScene(loginScene);
            stage.setTitle("Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}