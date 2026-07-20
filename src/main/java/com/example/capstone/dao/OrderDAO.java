package com.example.capstone.dao;

import com.example.capstone.model.Order;
import com.example.capstone.util.MySQLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public boolean save(Order order, String itemsSummary) {
        String sql = "INSERT INTO orders (orderId, customerId, status, totalAmount, itemsSummary) VALUES (?,?,?,?,?)";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, order.getOrderId());
            stmt.setString(2, order.getCustomer().getUserId());
            stmt.setString(3, order.getStatus());
            stmt.setDouble(4, order.getTotalAmount());
            stmt.setString(5, itemsSummary);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> getPendingOrders() {
        List<String> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE status = 'Pending'";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orders.add(rs.getString("orderId") + " - "
                        + rs.getString("itemsSummary") + " - ₱"
                        + rs.getDouble("totalAmount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public boolean updateStatus(String orderId, String newStatus) {
        String sql = "UPDATE orders SET status=? WHERE orderId=?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newStatus);
            stmt.setString(2, orderId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String orderId) {
        String sql = "DELETE FROM orders WHERE orderId=?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, orderId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}