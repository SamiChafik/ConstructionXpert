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
            case "editform":
                showEditForm(request, response);
                break;
            case "edit":
                updateRessource(request, response);
                break;
            case "delete":
                deleteRessource(request, response);
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

        response.sendRedirect("/ressource?action=list");
    }

    private void listRessources(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RessourceDAO ressourceDAO = new RessourceDAO();
        List<Ressource> ressource = ressourceDAO.getAllRessources();

        request.setAttribute("ressources", ressource);

        request.getRequestDispatcher("listRessource.jsp").forward(request, response);
    }

    private void deleteRessource(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ressourceId = Integer.parseInt(request.getParameter("ressourceId"));

        RessourceDAO ressourceDAO = new RessourceDAO();
        boolean isDeleted = ressourceDAO.deleteRessource(ressourceId);

        if (isDeleted) {
            response.sendRedirect("/ressource?action=list");
        } else {
            response.sendRedirect("viewProjects?error=delete_failed");
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int ressourceId = Integer.parseInt(request.getParameter("ressourceId"));
        Ressource ressource = ressourceDAO.getRessourceById(ressourceId);

        request.setAttribute("ressource_id", ressource.getRessource_id());
        request.setAttribute("name", ressource.getName());
        request.setAttribute("type", ressource.getType());
        request.setAttribute("quantity", ressource.getQuantity());
        request.setAttribute("supplier", ressource.getSupplier());

        request.getRequestDispatcher("editRessource.jsp").forward(request, response);
    }

    private void updateRessource(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int ressourceId = Integer.parseInt(request.getParameter("ressource_id"));
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String supplier = request.getParameter("supplier");

        Ressource ressource = new Ressource(ressourceId, name, type, quantity, supplier);
        ressourceDAO.updateRessource(ressource);

        response.sendRedirect("/ressource?action=list");
    }
}
