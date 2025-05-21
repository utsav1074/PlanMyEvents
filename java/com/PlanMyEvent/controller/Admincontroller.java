package com.PlanMyEvent.controller;

import com.PlanMyEvent.model.UserModel;
import com.PlanMyEvent.model.BookingAdminModel;
import com.PlanMyEvent.service.AdminUserService;
import com.PlanMyEvent.service.BookingAdminService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/Admin")
public class Admincontroller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Admincontroller() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            AdminUserService userService = new AdminUserService();
            List<UserModel> userList = userService.getAllUsers();

            BookingAdminService service = new BookingAdminService();
            double totalRevenue = service.getTotalRevenueFromBooked();
            int totalUsers = service.getTotalUsers();
            int totalBookedVenues = service.getTotalBookedVenues();

            request.setAttribute("userList", userList);
            request.setAttribute("totalRevenue", totalRevenue);
            request.setAttribute("totalUsers", totalUsers);
            request.setAttribute("totalBookedVenues", totalBookedVenues);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to load user data.");
        }

        request.getRequestDispatcher("/WEB-INF/Pages/Admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
