package com.PlanMyEvent.controller;

import com.PlanMyEvent.model.UserModel;
import com.PlanMyEvent.service.LoginService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(asyncSupported = true, urlPatterns = { "/Login" })
public class Logincontroller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Logincontroller() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/Pages/Login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserModel user = new UserModel();
        user.setUsername(username);
        user.setPassword(password);

        LoginService loginService = new LoginService();
        UserModel authenticatedUser = loginService.authenticate(user);

        if (authenticatedUser != null) {
            // ✅ Store full session details
            HttpSession session = request.getSession();
            session.setAttribute("username", authenticatedUser.getUsername());
            session.setAttribute("userId", authenticatedUser.getUserId()); // ✅ This was missing

            // ✅ Set role in cookie for filter access
            Cookie roleCookie = new Cookie("role", authenticatedUser.getUserRole());
            roleCookie.setMaxAge(60 * 60); // 1 hour
            response.addCookie(roleCookie);

            // ✅ Redirect to homepage
            response.sendRedirect(request.getContextPath() + "/index");
        } else {
            request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("/WEB-INF/Pages/Login.jsp").forward(request, response);
        }
    }
}
