package com.example.constructionxpert.DAO;

import com.example.constructionxpert.model.Project;

import java.sql.*;

public class ProjectDAO {
    DatabaseConnection dbConnection = new DatabaseConnection();

    public void addProject(Project project) {

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO project(name, description, start_date, finish_date, budget) VALUES (?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, project.getName());
            preparedStatement.setString(2, project.getDescription());
            preparedStatement.setDate(3, Date.valueOf(project.getStart_date()));
            preparedStatement.setDate(4, Date.valueOf(project.getFinish_date()));
            preparedStatement.setDouble(5, project.getBudget());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
    }
}
