package com.example.capstone.dao;

import com.example.capstone.model.MenuItem;
import com.example.capstone.model.Restaurant;
import com.example.capstone.util.MySQLConnection;
import java.sql.*;

public class RestaurantDAO {

    public Restaurant getRestaurantWithMenu(String restaurantId) {
        Restaurant restaurant = null;
        String restaurantSql = "SELECT * FROM restaurants WHERE restaurantId=?";
        String menuSql       = "SELECT * FROM menu_items WHERE restaurantId=?";

        try (Connection conn = MySQLConnection.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(restaurantSql)) {
                stmt.setString(1, restaurantId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    restaurant = new Restaurant(
                            rs.getString("restaurantId"),
                            rs.getString("name"),
                            rs.getString("address"));
                }
            }
            if (restaurant != null) {
                try (PreparedStatement stmt = conn.prepareStatement(menuSql)) {
                    stmt.setString(1, restaurantId);
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        MenuItem item = new MenuItem(
                                rs.getString("itemId"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getDouble("price"),
                                rs.getString("category"));
                        restaurant.addMenuItem(item);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurant;
    }
}