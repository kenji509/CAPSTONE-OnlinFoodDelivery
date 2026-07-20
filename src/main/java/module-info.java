module com.example.capstone {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;

    opens com.example.capstone.main to javafx.graphics, javafx.fxml;
    opens com.example.capstone.controller to javafx.fxml;
    opens com.example.capstone.dao to javafx.fxml;
    opens com.example.capstone.model to javafx.fxml;
    opens com.example.capstone.util to javafx.fxml;

    exports com.example.capstone.main;
    exports com.example.capstone.controller;
    exports com.example.capstone.dao;
    exports com.example.capstone.model;
    exports com.example.capstone.util;
}