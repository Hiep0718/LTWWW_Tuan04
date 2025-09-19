package iuh.fit.se.controllers;

import iuh.fit.se.utils.EntityManagerFactoryUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(name = "userController", urlPatterns = {"/users", "/users*"})
public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "new":
                handleShowRegisterForm(req, resp);
                break;
            case "delete":
                handleDeleteUser(req, resp);
                break;
            case "edit":
                handleShowEditForm(req, resp);
                break;
            default:
                handleShowUser(req, resp);
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "insert":
                handleAddUser(req, resp);
                break;
            case "update":
                handleEditUser(req, resp);
                break;
            default:
                handleShowUser(req, resp);
                break;
        }
    }
    private void handleShowRegisterForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    private void handleDeleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    private void handleShowEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    private void handleShowUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager()

        ) {

        }
    }
    private void handleAddUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    private void handleEditUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
