package com.example.capstone.dao;

import com.example.capstone.model.Admin;
import com.example.capstone.util.MySQLConnection;
import com.example.capstone.util.PasswordUtil;
import java.sql.*;

public class AdminDAO {

    public Admin login(String email, String password) {
        String sql = "SELECT * FROM admins WHERE email=?";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedHash = rs.getString("password");
                if (PasswordUtil.matches(password, storedHash)) {
                    return new Admin(
                            rs.getString("userId"),
                            rs.getString("name"),
                            rs.getString("email"),
                            storedHash,
                            rs.getString("contactNumber"),
                            rs.getString("accessLevel"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getTotalOrderCount() {
        String sql = "SELECT COUNT(*) AS cnt FROM orders";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt("cnt");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double getTotalRevenue() {
        String sql = "SELECT SUM(totalAmount) AS total FROM orders WHERE status != 'Cancelled'";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getDouble("total");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}