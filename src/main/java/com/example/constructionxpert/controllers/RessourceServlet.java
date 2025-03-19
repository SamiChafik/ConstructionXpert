package com.example.constructionxpert.controllers;

import com.example.constructionxpert.DAO.RessourceDAO;
import com.example.constructionxpert.model.Ressource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ressource")
public class RessourceServlet extends HttpServlet {

    private RessourceDAO ressourceDAO = new RessourceDAO();

    @Override
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
                addRessource(request, response);
                break;
        }
    }



    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("addRessource.jsp");
        dispatcher.forward(request, response);
    }

    private void addRessource(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String supplier = request.getParameter("supplier");

        Ressource ressource = new Ressource(name, type, quantity, supplier);

        ressource.setName(name);
        ressource.setType(type);
        ressource.setQuantity(quantity);
        ressource.setSupplier(supplier);

        ressourceDAO.addRessource(ressource);


    }
}
