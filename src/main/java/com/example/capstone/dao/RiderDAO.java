package com.example.capstone.dao;

import com.example.capstone.model.Rider;
import com.example.capstone.util.MySQLConnection;
import java.sql.*;

public class RiderDAO {

    public boolean register(Rider r, String password) {
        String sql = "INSERT INTO riders VALUES (?,?,?,?,?,?)";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, r.getUserId());
            stmt.setString(2, r.getName());
            stmt.setString(3, r.getEmail());
            stmt.setString(4, password);
            stmt.setString(5, r.getContactNumber());
            stmt.setString(6, r.getVehicleType());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Rider login(String email, String password) {
        String sql = "SELECT * FROM riders WHERE email=? AND password=?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Rider(
                        rs.getString("userId"),
                        rs.getString("name"),
                        rs.getString("email"),
                        password,
                        rs.getString("contactNumber"),
                        rs.getString("vehicleType"), "");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}