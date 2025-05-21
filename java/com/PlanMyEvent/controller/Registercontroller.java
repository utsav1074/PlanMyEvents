package com.PlanMyEvent.controller;

import com.PlanMyEvent.model.UserModel;
import com.PlanMyEvent.service.RegisterService;
import com.PlanMyEvent.util.PasswordUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet implementation class Registercontroller
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Register" })
public class Registercontroller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Registercontroller() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/Pages/Register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Step 1: Retrieve form inputs
        String fullName = request.getParameter("fullname");
        String email = request.getParameter("email");
        String contact = request.getParameter("contacts");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {

            // Step 2: Encrypt password
            String encryptedPassword = PasswordUtil.encrypt(username, password);

            // Step 3: Prepare UserModel
            UserModel newUser = new UserModel();
            newUser.setFullName(fullName);
            newUser.setEmail(email);
            newUser.setContact(contact);
            newUser.setUsername(username);
            newUser.setPassword(encryptedPassword);
            newUser.setUserRole("user"); // Default role

            // Step 4: Save to DB
            RegisterService registerService = new RegisterService();
            boolean isSuccess = registerService.addUser(newUser);

            if (isSuccess) {
            	response.sendRedirect(request.getContextPath() + "/Login"); // redirect after successful registration
            } else {
                request.setAttribute("error", "Registration failed. Please try again.");
                request.getRequestDispatcher("/WEB-INF/Pages/Register.jsp").forward(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("error", "Invalid input or system error.");
            request.getRequestDispatcher("/WEB-INF/Pages/Register.jsp").forward(request, response);
            e.printStackTrace();
        }
    }
}
