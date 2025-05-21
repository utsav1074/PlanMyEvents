package com.PlanMyEvent.controller;

import com.PlanMyEvent.config.DbConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet(asyncSupported = true, urlPatterns = { "/DeleteUser" })
public class DeleteUserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteUserController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userIdParam = request.getParameter("userId");

        if (userIdParam == null || userIdParam.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/Admin");
            return;
        }

        try {
            int userId = Integer.parseInt(userIdParam);

            try (Connection conn = DbConfig.getDbConnection()) {
                String sql = "DELETE FROM User WHERE UserId = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, userId);
                    stmt.executeUpdate();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/Admin");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        response.getWriter().write("GET method is not supported for DeleteUser");
    }
}
