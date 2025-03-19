package com.example.constructionxpert.DAO;

import com.example.constructionxpert.model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM project";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Project project = new Project();
                project.setProject_id(resultSet.getInt("project_id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setStart_date(resultSet.getString("start_date"));
                project.setFinish_date(resultSet.getString("finish_date"));
                project.setBudget(resultSet.getDouble("budget"));

                projects.add(project);
            }

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }

        return projects;
    }

    public boolean deleteProject(int projectId) {
        String sql = "DELETE FROM project WHERE project_id = ?";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, projectId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
