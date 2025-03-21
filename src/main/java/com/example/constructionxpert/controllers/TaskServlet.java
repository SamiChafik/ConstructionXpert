package com.example.constructionxpert.controllers;

import com.example.constructionxpert.DAO.RessourceDAO;
import com.example.constructionxpert.DAO.TaskDAO;
import com.example.constructionxpert.model.Ressource;
import com.example.constructionxpert.model.Task;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/task")
public class TaskServlet extends HttpServlet {

    private TaskDAO taskDAO = new TaskDAO();
    private RessourceDAO ressourceDAO = new RessourceDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String action = request.getParameter("action");

       switch (action) {
           case "new":
               showNewForm(request,response);
               break;
           case "add":
               addTask(request,response);
               break;
           case "list":
               listTasks(request,response);
               break;
           case "addressourceforn":
               showResourceToTaskForm(request, response);
               break;
           case "addressourcetotask":
               addResourceToTask(request, response);
               break;

       }
    }



    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int projectId = Integer.parseInt(request.getParameter("projectId"));

        request.setAttribute("projectId", projectId);

        request.getRequestDispatcher("addTask.jsp").forward(request, response);
    }

    private void addTask(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String startDate = request.getParameter("start_date");
        String finishDate = request.getParameter("finish_date");

        Task task = new Task();
        task.setProject_id(projectId);
        task.setName(name);
        task.setDescription(description);
        task.setStart_date(startDate);
        task.setFinish_date(finishDate);

        taskDAO.addTask(task);

        response.sendRedirect("project?action=list");
    }

    private void listTasks(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Task> tasks = taskDAO.getAllTasks();

        request.setAttribute("tasks", tasks);

        request.getRequestDispatcher("listTasks.jsp").forward(request, response);
    }

    private void showResourceToTaskForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("taskId"));

        List<Ressource> ressources = ressourceDAO.getAllRessources();

        request.setAttribute("taskId", taskId);
        request.setAttribute("ressources", ressources);

        request.getRequestDispatcher("addResourceToTask.jsp").forward(request, response);
    }

    private void addResourceToTask(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        int ressourceId = Integer.parseInt(request.getParameter("resourceId"));
        int quantity = Integer.parseInt(request.getParameter("resourceQuantity"));

        taskDAO.addTaskRessource(taskId, ressourceId, quantity);

        response.sendRedirect("task?action=list");
    }

}
