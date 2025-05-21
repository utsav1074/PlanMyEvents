package com.PlanMyEvent.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.PlanMyEvent.util.CookieUtil;
import com.PlanMyEvent.util.SessionUtil;

@WebFilter(asyncSupported = true, urlPatterns = "/*")
public class AuthenticationFilter implements Filter {

    private static final String LOGIN = "/Login";
    private static final String REGISTER = "/Register";
    private static final String PROFILE = "/profile.jsp"; // assuming JSP, or use servlet
    private static final String DASHBOARD = "/dashboard";
    private static final String LOGOUT = "/Logout";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Nothing needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();

        // Allow static resources always
        if (uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".jpg") || uri.endsWith(".png")) {
            chain.doFilter(request, response);
            return;
        }

        boolean isLoggedIn = SessionUtil.getAttribute(req, "username") != null;
        String userRole = CookieUtil.getCookie(req, "role") != null
                ? CookieUtil.getCookie(req, "role").getValue()
                : null;

        // ✅ Only restrict /profile.jsp and /dashboard
        if (uri.endsWith(PROFILE)) {
            if (!isLoggedIn) {
                res.sendRedirect(req.getContextPath() + LOGIN);
                return;
            }
        }

        if (uri.endsWith(DASHBOARD)) {
            if (!"admin".equals(userRole)) {
                res.sendRedirect(req.getContextPath() + LOGIN);
                return;
            }
        }

        // Allow all other pages
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Nothing needed
    }
}
