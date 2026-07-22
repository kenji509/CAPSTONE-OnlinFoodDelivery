package com.example.capstone.dao;

import com.example.capstone.model.Customer;
import com.example.capstone.util.MySQLConnection;
import java.sql.*;

public class CustomerDAO {

    public boolean register(Customer c) {
        String sql = "INSERT INTO customers VALUES (?,?,?,?,?,?)";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, c.getUserId());
            stmt.setString(2, c.getName());
            stmt.setString(3, c.getEmail());
            stmt.setString(4, c.getPassword());
            stmt.setString(5, c.getContactNumber());
            stmt.setString(6, c.getDeliveryAddress());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Customer login(String email, String password) {
        String sql = "SELECT * FROM customers WHERE email=? AND password=?";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getString("userId"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("contactNumber"),
                        rs.getString("deliveryAddress"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateProfile(String userId, String name, String contactNumber, String address) {
        String sql = "UPDATE customers SET name=?, contactNumber=?, deliveryAddress=? WHERE userId=?";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, contactNumber);
            stmt.setString(3, address);
            stmt.setString(4, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String userId) {
        String sql = "DELETE FROM customers WHERE userId=?";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}