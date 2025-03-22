package com.example.constructionxpert.controllers;

import com.example.constructionxpert.DAO.ProjectDAO;
import com.example.constructionxpert.model.Project;
import jakarta.servlet.RequestDispatcher;
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
            case "editform":
                showEditForm(request, response);
                break;
            case "edit":
                updateProject(request, response);
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

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        Project project = projectDAO.getProjectById(projectId);

        request.setAttribute("project_id", project.getProject_id());
        request.setAttribute("name", project.getName());
        request.setAttribute("description", project.getDescription());
        request.setAttribute("start_date", project.getStart_date());
        request.setAttribute("finish_date", project.getFinish_date());
        request.setAttribute("budget", project.getBudget());

        request.getRequestDispatcher("editProject.jsp").forward(request, response);
    }

    private void updateProject(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int projectId = Integer.parseInt(request.getParameter("projectId"));
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

        Project project = new Project(projectId, name, description, start_date, finish_date, budget);
        projectDAO.updateProject(project);

        response.sendRedirect("/project?action=list");
    }
}

