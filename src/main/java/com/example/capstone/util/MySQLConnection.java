package com.example.capstone.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    private static MySQLConnection instance;

    private static final String URL      = "jdbc:mysql://localhost:3306/fooddelivery";
    private static final String USER     = "root";
    private static final String PASSWORD = "";

    private MySQLConnection() {
    }

    public static MySQLConnection getInstance() {
        if (instance == null) {
            instance = new MySQLConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}