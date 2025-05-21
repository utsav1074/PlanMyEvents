<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>
<%@ page import="com.PlanMyEvent.util.SessionUtil" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css"/>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet"/>
<title>Insert title here</title>
</head>
<body>
 <header class="navbar">
    <div class="logo">
      <img src="${pageContext.request.contextPath}/images/logo.png" alt="Plan My Event Logo" />
    </div>
    <nav>
      <ul class="nav-links">
        <li><a href="${pageContext.request.contextPath}/index">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/Aboutus">About Us</a></li>
        <li><a href="${pageContext.request.contextPath}/Service">Services</a></li>
        <li><a href="${pageContext.request.contextPath}/Blog">Blog</a></li>
        <li><a href="${pageContext.request.contextPath}/Contact">Contact</a></li>

        <%
            String loggedInUser = (String) session.getAttribute("username");
            String contextPath = ((HttpServletRequest) request).getContextPath();
            String role = SessionUtil.getUserRoleFromCookies(request);
            String profileLink = contextPath + "/Login"; // Default

            if (loggedInUser != null) {
                if ("admin".equals(role)) {
                    profileLink = contextPath + "/Admin";
                } else if ("user".equals(role)) {
                    profileLink = contextPath + "/Profile";
                }
            }
        %>

        <!-- ✅ Profile Icon link -->
        <li><a href="<%= profileLink %>"><i class="fas fa-user-circle profile-icon"></i></a></li>

        <% if (loggedInUser != null) { %>
            <!-- ✅ Logout link shown only when logged in -->
            <li><a href="<%= contextPath %>/Logout">Logout</a></li>
        <% } else { %>
            <!-- Login and Register links shown only when not logged in -->
            <li><a href="${pageContext.request.contextPath}/Login">Login</a></li>
            <li><a href="${pageContext.request.contextPath}/Register">Register</a></li>
        <% } %>
      </ul>
    </nav>
 </header>
</body>
</html>
