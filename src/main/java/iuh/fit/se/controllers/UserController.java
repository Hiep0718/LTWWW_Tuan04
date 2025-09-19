package iuh.fit.se.controllers;

import iuh.fit.se.daos.UserDAO;
import iuh.fit.se.daos.impl.UserDaoImpl;
import iuh.fit.se.entities.User;
import iuh.fit.se.utils.EntityManagerFactoryUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.io.IOException;
import java.util.List;
import java.util.Set;

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
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/user/add.jsp");
        dispatcher.forward(req, resp);
    }
    private void handleDeleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    private void handleShowEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager()) {
            UserDAO userDao = new UserDaoImpl(entityManager);
            int id = Integer.parseInt(req.getParameter("id"));
            User existingUser = userDao.findById(id);
            RequestDispatcher dispatcher = req.getRequestDispatcher("views/user/edit.jsp");
            req.setAttribute("user", existingUser);
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void handleShowUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager();
        ) {
            UserDAO userDAO = new UserDaoImpl(entityManager);
            List<User> listUser = userDAO.findAll();

            req.setAttribute("listUser", listUser);
            req.getRequestDispatcher("views/user/index.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void handleAddUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager();) {
            UserDAO userDAO = new UserDaoImpl(entityManager);
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String birthDate = req.getParameter("birthDate");
            String gender = req.getParameter("gender");
            User user = new User(firstName, lastName, email, password, birthDate, gender);
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<User>> violationSet = validator.validate(user);
            if (violationSet.isEmpty()) {
                userDAO.save(user);
                resp.sendRedirect("users");
            } else {
               RequestDispatcher dispatcher = req.getRequestDispatcher("views/user/add.jsp");
               StringBuilder errorMessage = new StringBuilder();
               violationSet.forEach(violation -> {
                     errorMessage.append(violation.getPropertyPath() + ": " + violation.getMessage());
                     errorMessage.append("<br />");
                });

               req.setAttribute("user", user);
                req.setAttribute("errorMessage", errorMessage);
                dispatcher.forward(req, resp);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void handleEditUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void destroy() {
        EntityManagerFactoryUtil.close();
        super.destroy();
    }

}
