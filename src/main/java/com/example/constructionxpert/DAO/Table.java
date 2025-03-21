package com.example.constructionxpert.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Table {

    public void createTables() {
        DatabaseConnection dbConnection = new DatabaseConnection();

        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement()) {

            // Create project table
            String sqlQuery = "CREATE TABLE IF NOT EXISTS project (" +
                    "project_id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "description TEXT NOT NULL, " +
                    "start_date DATE NOT NULL, " +
                    "finish_date DATE NOT NULL, " +
                    "budget BIGINT NOT NULL " +
                    ")";

            // Create ressource table
            String sqlQuery2 = "CREATE TABLE IF NOT EXISTS ressource (" +
                    "ressource_id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "type VARCHAR(100) NOT NULL, " +
                    "quantity INT NOT NULL, " +
                    "supplier VARCHAR(100) NOT NULL " +
                    ")";

            // Create tache table
            String sqlQuery3 = "CREATE TABLE IF NOT EXISTS tache (" +
                    "tache_id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "description TEXT NOT NULL, " +
                    "start_date DATE NOT NULL, " +
                    "finish_date DATE NOT NULL, " +
                    "project_id INT NOT NULL, " +
                    "FOREIGN KEY (project_id) REFERENCES project(project_id) ON DELETE CASCADE" +
                    ")";

            // Create tache_ressource table
            String sqlQuery4 = "CREATE TABLE IF NOT EXISTS tache_ressource (" +
                    "tache_ressource_id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "tache_id INT NOT NULL, " +
                    "ressource_id INT NOT NULL, " +
                    "quantity INT NOT NULL," +
                    "FOREIGN KEY (tache_id) REFERENCES tache(tache_id) ON DELETE CASCADE, " +
                    "FOREIGN KEY (ressource_id) REFERENCES ressource(ressource_id) ON DELETE CASCADE" +
                    ")";

            // Create user table
            String sqlQuery5 = "CREATE TABLE IF NOT EXISTS user (" +
                    "user_id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "email VARCHAR(100) NOT NULL, " +
                    "password VARCHAR(100) NOT NULL " +
                    ")";

            // Execute all table creation queries
            statement.executeUpdate(sqlQuery);
            System.out.println("Table 'project' created successfully!");

            statement.executeUpdate(sqlQuery2);
            System.out.println("Table 'ressource' created successfully!");

            statement.executeUpdate(sqlQuery3);
            System.out.println("Table 'tache' created successfully!");

            statement.executeUpdate(sqlQuery4);
            System.out.println("Table 'tach_ressource' created successfully!");

            statement.executeUpdate(sqlQuery5);
            System.out.println("Table 'user' created successfully!");

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
    }
}