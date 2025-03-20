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

       }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int projectId = Integer.parseInt(request.getParameter("projectId"));

        List<Ressource> ressources = ressourceDAO.getAllRessources();
        request.setAttribute("ressources", ressources);

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
        String[] ressourceIds = request.getParameterValues("ressource_ids");

        Task task = new Task();
        task.setProject_id(projectId);
        task.setName(name);
        task.setDescription(description);
        task.setStart_date(startDate);
        task.setFinish_date(finishDate);

        taskDAO.addTask(task);

        if (ressourceIds != null) {
            for (String ressourceId : ressourceIds) {
                taskDAO.addTaskRessource(task.getTask_id(), Integer.parseInt(ressourceId));
            }
        }

        response.sendRedirect("project?action=list");
    }

}
