package com.example.constructionxpert.servlet;

import com.example.constructionxpert.DAO.Table;
import com.example.constructionxpert.DAO.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

private Table table;

    public void init() {
        table = new Table();
        table.createTables();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        boolean isValidUser = userDAO.checkLogin(email, password);

        if (isValidUser) {
            response.sendRedirect("dashboard.jsp");
        } else {
            response.sendRedirect("Login.jsp?error=invalid_credentials");
        }
    }
}