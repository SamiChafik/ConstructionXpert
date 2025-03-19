package com.example.constructionxpert.controllers;

import com.example.constructionxpert.DAO.ProjectDAO;
import com.example.constructionxpert.DAO.RessourceDAO;
import com.example.constructionxpert.model.Project;
import com.example.constructionxpert.model.Ressource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

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
            case "list":
                listRessources(request, response);
                break;

        }
    }



    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("addRessource.jsp");
        dispatcher.forward(request, response);
    }

    private void addRessource(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

        response.sendRedirect("/ressource?action=lsit");
    }

    private void listRessources(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RessourceDAO ressourceDAO = new RessourceDAO();
        List<Ressource> ressource = ressourceDAO.getAllRessources();

        request.setAttribute("ressources", ressource);

        request.getRequestDispatcher("listRessource.jsp").forward(request, response);
    }
}
