package com.example.constructionxpert.DAO;

import com.example.constructionxpert.model.Project;
import com.example.constructionxpert.model.Ressource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RessourceDAO {
    DatabaseConnection dbConnection = new DatabaseConnection();

    public void addRessource(Ressource ressource) {

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ressource(name, type, quantity, supplier) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setString(1, ressource.getName());
            preparedStatement.setString(2, ressource.getType());
            preparedStatement.setInt(3, ressource.getQuantity());
            preparedStatement.setString(4, ressource.getSupplier());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
    }
}
