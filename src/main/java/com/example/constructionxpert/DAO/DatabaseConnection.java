package com.example.constructionxpert.DAO;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private String jdbcURL = "jdbc:mysql://localhost:3306/constructionxpert";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456789";
    private String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    protected java.sql.Connection getConnection() {
        try {
            Class.forName(jdbcDriver);
            return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}