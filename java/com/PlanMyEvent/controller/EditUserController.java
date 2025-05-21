package com.PlanMyEvent.controller;

import com.PlanMyEvent.config.DbConfig;
import com.PlanMyEvent.model.UserModel;
import com.PlanMyEvent.util.PasswordUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

@WebServlet(asyncSupported = true, urlPatterns = { "/EditUser" })
public class EditUserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditUserController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userIdParam = request.getParameter("userId");
        String source = request.getParameter("source");

        if (userIdParam == null) {
            response.sendRedirect(request.getContextPath() + "/Admin");
            return;
        }

        try (Connection conn = DbConfig.getDbConnection()) {
            String sql = "SELECT * FROM User WHERE UserId = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, Integer.parseInt(userIdParam));
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    UserModel user = new UserModel();
                    user.setUserId(rs.getInt("UserId"));
                    user.setFullName(rs.getString("FullName"));
                    user.setUsername(rs.getString("Username"));
                    user.setEmail(rs.getString("Email"));
                    user.setContact(rs.getString("Contact"));
                    user.setPassword(PasswordUtil.decrypt(rs.getString("Password"), rs.getString("Username")));
                    request.setAttribute("user", user);
                    request.setAttribute("source", source); // pass source to JSP
                    request.getRequestDispatcher("/WEB-INF/Pages/EditUser.jsp").forward(request, response);
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/Admin");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            String fullName = request.getParameter("fullName");
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String contact = request.getParameter("contact");
            String password = request.getParameter("password");
            String source = request.getParameter("source");

            String encryptedPassword = PasswordUtil.encrypt(username, password);

            try (Connection conn = DbConfig.getDbConnection()) {
                String sql = "UPDATE User SET FullName=?, Username=?, Email=?, Contact=?, Password=? WHERE UserId=?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, fullName);
                    stmt.setString(2, username);
                    stmt.setString(3, email);
                    stmt.setString(4, contact);
                    stmt.setString(5, encryptedPassword);
                    stmt.setInt(6, userId);
                    stmt.executeUpdate();
                }
            }

            // ✅ Redirect based on source
            if ("profile".equalsIgnoreCase(source)) {
                response.sendRedirect(request.getContextPath() + "/Profile");
            } else {
                response.sendRedirect(request.getContextPath() + "/Admin");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/Error");
        }
    }
}
