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

@WebServlet(asyncSupported = true, urlPatterns = { "/CancelBooking" })
public class CancelBookingcontroller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CancelBookingcontroller() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = (String) request.getSession().getAttribute("username");
        String bookingIdParam = request.getParameter("bookingId");
        String source = request.getParameter("source");

        if (username == null || bookingIdParam == null || bookingIdParam.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/Login");
            return;
        }

        try {
            int bookingId = Integer.parseInt(bookingIdParam);

            try (Connection conn = DbConfig.getDbConnection()) {
                String sql = "UPDATE UserVenueFoodDecorationBooking SET BookingStatus = ? WHERE BookingId = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, "Cancelled");
                    stmt.setInt(2, bookingId);
                    stmt.executeUpdate();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if ("profile".equalsIgnoreCase(source)) {
            response.sendRedirect(request.getContextPath() + "/Profile");
        } else {
            response.sendRedirect(request.getContextPath() + "/Admin-Booking");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Not used
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        response.getWriter().write("GET method is not supported for CancelBooking");
    }
}
