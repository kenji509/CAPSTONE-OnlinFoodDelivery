package com.example.capstone.dao;

import com.example.capstone.model.Rider;
import com.example.capstone.util.MySQLConnection;
import com.example.capstone.util.PasswordUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RiderDAO {

    public boolean emailExists(String email) {
        String sql = "SELECT 1 FROM riders WHERE email=?";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean register(Rider r, String password) {
        String sql = "INSERT INTO riders VALUES (?,?,?,?,?,?)";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, r.getUserId());
            stmt.setString(2, r.getName());
            stmt.setString(3, r.getEmail());
            stmt.setString(4, PasswordUtil.hash(password));
            stmt.setString(5, r.getContactNumber());
            stmt.setString(6, r.getVehicleType());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Rider login(String email, String password) {
        String sql = "SELECT * FROM riders WHERE email=?";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedHash = rs.getString("password");
                if (PasswordUtil.matches(password, storedHash)) {
                    return new Rider(
                            rs.getString("userId"),
                            rs.getString("name"),
                            rs.getString("email"),
                            storedHash,
                            rs.getString("contactNumber"),
                            rs.getString("vehicleType"), "");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Rider> getAllRiders() {
        List<Rider> riders = new ArrayList<>();
        String sql = "SELECT * FROM riders";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                riders.add(new Rider(
                        rs.getString("userId"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("contactNumber"),
                        rs.getString("vehicleType"), ""));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return riders;
    }

    public boolean updateProfile(String userId, String name, String contactNumber, String vehicleType) {
        String sql = "UPDATE riders SET name=?, contactNumber=?, vehicleType=? WHERE userId=?";
        try (Connection conn = MySQLConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, contactNumber);
            stmt.setString(3, vehicleType);
            stmt.setString(4, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}