package com.PlanMyEvent.controller;

import com.PlanMyEvent.model.UserModel;
import com.PlanMyEvent.model.BookingHistoryModel;
import com.PlanMyEvent.service.LoginService;
import com.PlanMyEvent.service.BookingHistoryService;
import com.PlanMyEvent.util.ImageUtil;
import com.PlanMyEvent.util.ValidationUtil;
import com.PlanMyEvent.config.DbConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@WebServlet(asyncSupported = true, urlPatterns = { "/Profile" })
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,
    maxFileSize = 1024 * 1024 * 5,
    maxRequestSize = 1024 * 1024 * 10
)
public class Profilecontroller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Profilecontroller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");

        if (username == null) {
            response.sendRedirect(request.getContextPath() + "/Login");
            return;
        }

        LoginService loginService = new LoginService();
        UserModel userDetails = loginService.getUserByUsername(username);

        if (userDetails != null) {
            request.setAttribute("user", userDetails);

            // ✅ Add booking history
            BookingHistoryService historyService = new BookingHistoryService();
            List<BookingHistoryModel> bookingList = historyService.getUserBookingHistory(userDetails.getUserId());

            request.setAttribute("bookingHistory", bookingList);
        }

        request.getRequestDispatcher("/WEB-INF/Pages/Profile.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");

        if (username == null) {
            response.sendRedirect(request.getContextPath() + "/Login");
            return;
        }

        Part imagePart = request.getPart("profileImage");

        if (imagePart != null && imagePart.getSize() > 0) {
            if (!ValidationUtil.isValidImageExtension(imagePart)) {
                request.getSession().setAttribute("errorMessage", "Only JPG, PNG, JPEG, or GIF files are allowed.");
                response.sendRedirect(request.getContextPath() + "/Profile");
                return;
            }

            ImageUtil imageUtil = new ImageUtil();
            String uploadedFileName = imageUtil.uploadImage(imagePart, "Profile");

            if (uploadedFileName != null) {
                try (Connection conn = DbConfig.getDbConnection()) {
                    String sql = "UPDATE User SET image_path = ? WHERE Username = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, uploadedFileName);
                    stmt.setString(2, username);
                    stmt.executeUpdate();
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        response.sendRedirect(request.getContextPath() + "/Profile");
    }
}
