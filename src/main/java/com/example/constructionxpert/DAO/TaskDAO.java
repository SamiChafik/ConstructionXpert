package com.example.constructionxpert.DAO;

import com.example.constructionxpert.model.Project;
import com.example.constructionxpert.model.Ressource;
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

    public void addTaskRessource(int taskId, int ressourceId, int quantity) {
        String sql = "INSERT INTO tache_ressource (tache_id, ressource_id, quantity) VALUES (?, ?, ?)";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, taskId);
            preparedStatement.setInt(2, ressourceId);
            preparedStatement.setInt(3, quantity);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT t.*, p.name AS project_name, r.name AS ressource_name, r.type AS ressource_type " +
                "FROM tache t " +
                "LEFT JOIN project p ON t.project_id = p.project_id " +
                "LEFT JOIN tache_ressource tr ON t.tache_id = tr.tache_id " +
                "LEFT JOIN ressource r ON tr.ressource_id = r.ressource_id";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int taskId = resultSet.getInt("tache_id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String startDate = resultSet.getString("start_date");
                String finishDate = resultSet.getString("finish_date");
                int projectId = resultSet.getInt("project_id");

                // Fetch project details
                Project project = new Project();
                project.setProject_id(projectId);
                project.setName(resultSet.getString("project_name"));

                // Fetch resource details
                Ressource ressource = new Ressource();
                ressource.setName(resultSet.getString("ressource_name"));
                ressource.setType(resultSet.getString("ressource_type"));

                // Check if the task already exists in the list
                Task existingTask = tasks.stream()
                        .filter(task -> task.getTask_id() == taskId)
                        .findFirst()
                        .orElse(null);

                if (existingTask == null) {
                    // Create a new task
                    Task task = new Task();
                    task.setTask_id(taskId);
                    task.setName(name);
                    task.setDescription(description);
                    task.setStart_date(startDate);
                    task.setFinish_date(finishDate);
                    task.setProject_id(projectId);
                    task.setProject(project);

                    // Add resource to the task
                    List<Ressource> ressources = new ArrayList<>();
                    ressources.add(ressource);
                    task.setRessources(ressources);

                    tasks.add(task);
                } else {
                    // Add the resource to the existing task
                    existingTask.getRessources().add(ressource);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }

}