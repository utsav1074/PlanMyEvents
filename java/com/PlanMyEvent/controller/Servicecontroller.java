package com.PlanMyEvent.controller;

import com.PlanMyEvent.model.BookingModel;
import com.PlanMyEvent.model.UserVenueFoodDecorationBookingModel;
import com.PlanMyEvent.service.BookingService;
import com.PlanMyEvent.service.UserVenueFoodDecorationBookingService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(asyncSupported = true, urlPatterns = { "/Service" })
public class Servicecontroller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Servicecontroller() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/Pages/Service.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/Login");
            return;
        }

        try {
            int userId = Integer.parseInt(session.getAttribute("userId").toString());

            // Common fields
            int venueId = Integer.parseInt(request.getParameter("venueId"));
            LocalDate bookingDate = LocalDate.parse(request.getParameter("bookingDate"));
            int guestCount = Integer.parseInt(request.getParameter("guestCount"));

            String finalSubmission = request.getParameter("finalSubmission");

            BookingService bookingService = new BookingService();

            // STEP 1: Check availability (no finalSubmission flag)
            if (finalSubmission == null) {
                boolean isBooked = bookingService.isVenueBookedOnDate(venueId, bookingDate);

                request.setAttribute("venueId", venueId);
                request.setAttribute("bookingDate", bookingDate);
                request.setAttribute("guestCount", guestCount);

                if (isBooked) {
                    request.setAttribute("showDatePopup", true);
                    request.setAttribute("bookingError", "This venue is already booked for the selected date. Please choose a different date.");
                } else {
                    request.setAttribute("proceedToAddOns", true);
                }

                request.getRequestDispatcher("/WEB-INF/Pages/Service.jsp").forward(request, response);
                return;
            }

            // STEP 2: Final Submission — must recheck booking status
            boolean isAlreadyBooked = bookingService.isVenueBookedOnDate(venueId, bookingDate);
            if (isAlreadyBooked) {
                request.setAttribute("venueId", venueId);
                request.setAttribute("bookingDate", bookingDate);
                request.setAttribute("guestCount", guestCount);
                request.setAttribute("showDatePopup", true);
                request.setAttribute("bookingError", "This venue is already booked for the selected date. Please choose a different date.");
                request.getRequestDispatcher("/WEB-INF/Pages/Service.jsp").forward(request, response);
                return;
            }

            // Handle optional add-ons (default to 0 if not selected)
            String foodParam = request.getParameter("food-package");
            String decorParam = request.getParameter("decoration");

            int foodId = (foodParam != null && !foodParam.isEmpty()) ? Integer.parseInt(foodParam) : 0;
            int decorId = (decorParam != null && !decorParam.isEmpty()) ? Integer.parseInt(decorParam) : 0;

            double venueCost = bookingService.getVenueCostById(venueId);
            double foodCost = (foodId > 0) ? bookingService.getFoodCostById(foodId) * guestCount : 0;
            double decorCost = (decorId > 0) ? bookingService.getDecorationCostById(decorId) : 0;
            double totalAmount = venueCost + foodCost + decorCost;

            BookingModel booking = new BookingModel(bookingDate, guestCount, totalAmount);
            int bookingId = bookingService.createBooking(booking);

            if (bookingId <= 0) {
                request.setAttribute("error", "Booking creation failed.");
                request.getRequestDispatcher("/WEB-INF/Pages/Service.jsp").forward(request, response);
                return;
            }

            UserVenueFoodDecorationBookingModel link = new UserVenueFoodDecorationBookingModel(
                userId, venueId, foodId, decorId, bookingId, "Booked"
            );

            boolean linked = new UserVenueFoodDecorationBookingService().save(link);

            if (linked) {
                request.setAttribute("success", "Booking successful!");
            } else {
                request.setAttribute("error", "Failed to link booking.");
            }

            request.getRequestDispatcher("/WEB-INF/Pages/Service.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Unexpected error: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/Pages/Service.jsp").forward(request, response);
        }
    }
}
