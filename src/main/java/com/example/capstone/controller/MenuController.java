package com.example.capstone.controller;

import com.example.capstone.dao.RestaurantDAO;
import com.example.capstone.model.Customer;
import com.example.capstone.model.MenuItem;
import com.example.capstone.model.OrderItem;
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
import java.util.ArrayList;
import java.util.List;

public class MenuController {

    private static final double WINDOW_WIDTH  = 400;
    private static final double WINDOW_HEIGHT = 720;

    @FXML private ListView<MenuItem> menuListView;
    @FXML private Label cartLabel;
    @FXML private Label restaurantNameLabel;

    private Restaurant restaurant;
    private List<OrderItem> cartItems = new ArrayList<>();
    private Customer loggedInCustomer;
    private String restaurantId = "R1";

    public void setCustomer(Customer customer) {
        this.loggedInCustomer = customer;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
        loadMenu();
    }

    @FXML
    public void initialize() {
        loadMenu();

        menuListView.setCellFactory(list -> new ListCell<>() {
            @Override
            protected void updateItem(MenuItem item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    ImageView photo = new ImageView(loadFoodImage(item.getName()));
                    photo.setFitWidth(60);
                    photo.setFitHeight(60);
                    photo.setPreserveRatio(true);

                    Label name = new Label(item.getName());
                    name.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

                    Label price = new Label("₱" + item.getPrice());
                    price.setStyle("-fx-font-size: 12px; -fx-text-fill: #D9480F;");

                    VBox textBox = new VBox(2, name, price);
                    HBox row = new HBox(12, photo, textBox);
                    row.setAlignment(Pos.CENTER_LEFT);
                    row.setPadding(new Insets(2));

                    setGraphic(row);
                    setText(null);
                }
            }
        });
    }

    private Image loadFoodImage(String itemName) {
        String filename = switch (itemName) {
            case "Chickenjoy"        -> "chicken_joy.jpg";
            case "Jolly Spaghetti"   -> "Jolly_Spaghetti.jpg";
            case "Peach Mango Pie"   -> "Peach_Mango_Pie.jpg";
            case "Burger Steak"      -> "Burger_Steak.jpg";
            case "Chicken Inasal"    -> "Chicken_Inasal.jpg";
            case "Halo-Halo"         -> "Halo-Halo.jpg";
            case "Beef Wanton Mami"  -> "Beef_Wanton_Mami.jpg";
            case "Siopao Asado"      -> "Siopao_sado.jpg";
            default -> null;
        };
        if (filename == null) return null;
        return new Image(getClass().getResourceAsStream("/com/example/capstone/images/" + filename));
    }

    private void loadMenu() {
        RestaurantDAO restaurantDAO = new RestaurantDAO();
        restaurant = restaurantDAO.getRestaurantWithMenu(restaurantId);
        restaurantNameLabel.setText(restaurant.getName());
        ObservableList<MenuItem> displayItems = FXCollections.observableArrayList(restaurant.getMenu());
        menuListView.setItems(displayItems);
        cartItems.clear();
        cartLabel.setText("Cart: 0 items");
    }

    @FXML
    protected void onAddToCartClick() {
        MenuItem selected = menuListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            cartItems.add(new OrderItem(selected, 1, ""));
            cartLabel.setText("Cart: " + cartItems.size() + " items");
        }
    }

    @FXML
    protected void onCheckoutClick() {
        if (cartItems.isEmpty()) {
            cartLabel.setText("Cart is empty - add items first");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/capstone/cart-view.fxml"));
            Scene cartScene = new Scene(loader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
            CartController cartController = loader.getController();
            cartController.setCartData(cartItems, restaurant, loggedInCustomer);
            Stage stage = (Stage) cartLabel.getScene().getWindow();
            stage.setScene(cartScene);
            stage.setTitle("Cart");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onViewOrderHistoryClick() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/capstone/order-history-view.fxml"));
            Scene historyScene = new Scene(loader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
            OrderHistoryController historyController = loader.getController();
            historyController.setCustomer(loggedInCustomer);
            Stage stage = (Stage) cartLabel.getScene().getWindow();
            stage.setScene(historyScene);
            stage.setTitle("Order History");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onBackToRestaurantsClick() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/capstone/restaurant-selection-view.fxml"));
            Scene selectionScene = new Scene(loader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
            RestaurantSelectionController selectionController = loader.getController();
            selectionController.setCustomer(loggedInCustomer);
            Stage stage = (Stage) cartLabel.getScene().getWindow();
            stage.setScene(selectionScene);
            stage.setTitle("Choose a Restaurant");
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
            Stage stage = (Stage) cartLabel.getScene().getWindow();
            stage.setScene(loginScene);
            stage.setTitle("Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}