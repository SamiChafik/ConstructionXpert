package com.example.constructionxpert.DAO;

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

    public boolean deleteRessource(int ressourceId) {
        String sql = "DELETE FROM ressource WHERE ressource_id = ?";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, ressourceId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Ressource getRessourceById(int ressourceId) {
        String sql = "SELECT * FROM ressource WHERE ressource_id = ?";
        Ressource ressource = null;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, ressourceId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ressource = new Ressource(
                            resultSet.getInt("ressource_id"),
                            resultSet.getString("name"),
                            resultSet.getString("type"),
                            resultSet.getInt("quantity"),
                            resultSet.getString("supplier")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }

        return ressource;
    }

    public void updateRessource(Ressource ressource) {
        String sql = "UPDATE ressource SET name = ?, type = ?, quantity = ?, supplier = ? WHERE ressource_id = ?";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, ressource.getName());
            statement.setString(2, ressource.getType());
            statement.setInt(3, ressource.getQuantity());
            statement.setString(4, ressource.getSupplier());
            statement.setInt(5, ressource.getRessource_id());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
