package com.PlanMyEvent.controller;

import com.PlanMyEvent.model.BookingAdminModel;
import com.PlanMyEvent.service.BookingAdminService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(asyncSupported = true, urlPatterns = { "/Admin-Booking" })
public class AdminBookingcontroller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminBookingcontroller() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<BookingAdminModel> allBookings = new BookingAdminService().getAllBookings();
        request.setAttribute("allBookings", allBookings);
        request.getRequestDispatcher("/WEB-INF/Pages/Admin-Booking.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
