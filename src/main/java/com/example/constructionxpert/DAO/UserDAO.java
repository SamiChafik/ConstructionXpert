package com.example.constructionxpert.DAO;

import com.example.constructionxpert.DAO.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public boolean checkLogin(String email, String password) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}