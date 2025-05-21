package com.PlanMyEvent.controller;

import com.PlanMyEvent.util.CookieUtil;
import com.PlanMyEvent.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(asyncSupported = true, urlPatterns = { "/Logout" })
public class Logoutcontroller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Logoutcontroller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Invalidate session
        SessionUtil.invalidateSession(request);

        // Delete role cookie
        CookieUtil.deleteCookie(response, "role");

        // Redirect to homepage
        response.sendRedirect(request.getContextPath() + "/index");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
