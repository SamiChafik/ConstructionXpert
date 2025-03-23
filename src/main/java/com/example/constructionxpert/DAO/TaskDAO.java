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
        String updateSql = "UPDATE ressource SET quantity = quantity - ? WHERE ressource_id = ?";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(sql);
             PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {

            insertStatement.setInt(1, taskId);
            insertStatement.setInt(2, ressourceId);
            insertStatement.setInt(3, quantity);

            insertStatement.executeUpdate();

            updateStatement.setInt(1, quantity);
            updateStatement.setInt(2, ressourceId);

            updateStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT t.*, p.name AS project_name, r.name AS ressource_name, r.type AS ressource_type, tr.tache_ressource_id " +
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
                ressource.setTacheRessourceId(resultSet.getInt("tache_ressource_id")); // Set tache_ressource_id

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

    public Task getTaskById(int taskId) {
        String sql = "SELECT * FROM tache WHERE tache_id = ?";
        Task task = null;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, taskId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    task = new Task(
                            resultSet.getInt("tache_id"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getString("start_date"),
                            resultSet.getString("finish_date")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }

        return task;
    }

    public boolean deleteTask(int taskId) {
        String sql = "DELETE FROM tache WHERE tache_id = ?";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, taskId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteRessourceTask(int taskRessourceId) {
        String sql = "DELETE FROM tache_ressource WHERE tache_ressource_id = ?";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, taskRessourceId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}