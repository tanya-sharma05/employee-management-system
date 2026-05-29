package com.ems.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/ems_db";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "05102005";

    private static Connection connection = null;

    private DatabaseConnection() {};

    public static Connection getConnection() {
        try {
            if(connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                System.out.println("[INFO] Database connected successfully!");
            }
        }
        catch(SQLException e) {
            System.out.println("[ERROR] Database connection failed!" + e.getMessage());
        }

        return connection;
    }
}