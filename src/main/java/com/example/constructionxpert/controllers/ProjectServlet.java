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

        response.sendRedirect("/project?action=listproject");
    }
}

