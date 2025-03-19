package com.example.constructionxpert.DAO;

import com.example.constructionxpert.model.Project;
import com.example.constructionxpert.model.Ressource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<Ressource> getAllRessources() {
        List<Ressource> ressources = new ArrayList<>();
        String sql = "SELECT * FROM ressource";

        try (Connection connection = dbConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Ressource ressource = new Ressource();

                ressource.setRessource_id(resultSet.getInt("ressource_id"));
                ressource.setName(resultSet.getString("name"));
                ressource.setType(resultSet.getString("type"));
                ressource.setQuantity(resultSet.getInt("quantity"));
                ressource.setSupplier(resultSet.getString("supplier"));

                ressources.add(ressource);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ressources;
    }
}
