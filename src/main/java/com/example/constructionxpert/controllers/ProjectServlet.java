package com.example.constructionxpert.controllers;

import com.example.constructionxpert.DAO.ProjectDAO;
import com.example.constructionxpert.model.Project;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/project")
public class ProjectServlet extends HttpServlet {

    private ProjectDAO projectDAO = new ProjectDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "add":
                addProject(request, response);
                break;
            case "list":
                listProject(request, response);
                break;
            case "delete":
                deleteProject(request, response);
                break;

        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("addProject.jsp");
        dispatcher.forward(request, response);
    }

    private void addProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String start_date = request.getParameter("start_date");
        String finish_date = request.getParameter("finish_date");
        String budgetStr = request.getParameter("budget");

        double budget = 0.0;
        try {
            budget = Double.parseDouble(budgetStr);
        } catch (NumberFormatException e) {
            System.err.println("Invalid budget value: " + budgetStr);
            e.printStackTrace();
        }

        Project project = new Project();

        project.setName(name);
        project.setDescription(description);
        project.setStart_date(start_date);
        project.setFinish_date(finish_date);
        project.setBudget(budget);

        projectDAO.addProject(project);

        response.sendRedirect("/project?action=list");
    }

    private void listProject(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ProjectDAO projectDAO = new ProjectDAO();
        List<Project> projects = projectDAO.getAllProjects();

        request.setAttribute("projects", projects);

        request.getRequestDispatcher("listProjects.jsp").forward(request, response);
    }

    private void deleteProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int projectId = Integer.parseInt(request.getParameter("projectId"));

        ProjectDAO projectDAO = new ProjectDAO();
        boolean isDeleted = projectDAO.deleteProject(projectId);

        if (isDeleted) {
            response.sendRedirect("/project?action=list");
        } else {
            response.sendRedirect("viewProjects?error=delete_failed");
        }
    }

}

