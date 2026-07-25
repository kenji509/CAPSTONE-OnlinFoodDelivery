package com.example.capstone.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    private static MySQLConnection instance;

    private static final String URL      = "jdbc:mysql://127.0.0.1:3306/fooddelivery";
    private static final String USER     = "root";
    private static final String PASSWORD = "";

    private MySQLConnection() {
    }

    public static synchronized MySQLConnection getInstance() {
        if (instance == null) {
            instance = new MySQLConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}