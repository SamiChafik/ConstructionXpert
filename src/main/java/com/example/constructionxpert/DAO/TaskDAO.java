package com.example.constructionxpert.DAO;

import com.example.constructionxpert.model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    DatabaseConnection dbConnection = new DatabaseConnection();

    public void addTask(Task task) {
        String sql = "INSERT INTO tache (name, description, start_date, finish_date, project_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, task.getName());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setString(3, task.getStart_date());
            preparedStatement.setString(4, task.getFinish_date());
            preparedStatement.setInt(5, task.getProject_id());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int taskId = generatedKeys.getInt(1);
                        task.setTask_id(taskId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTaskRessource(int taskId, int ressourceId) {
        String sql = "INSERT INTO tache_ressource (tache_id, ressource_id) VALUES (?, ?)";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, taskId);
            preparedStatement.setInt(2, ressourceId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}