<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Admin Dashboard - User Management</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Admin.css"/>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet"/>
</head>
<body>

<jsp:include page="header.jsp"/>

<!-- Admin Dashboard Navbar -->
<div class="admin-navbar">
  <div class="brand">Admin Dashboard</div>
  <div class="nav-links">
    <a href="${pageContext.request.contextPath}/Admin" class="active">User Management</a>
    <a href="${pageContext.request.contextPath}/Admin-Booking">Booking Management</a>
  </div>
</div>

<br>

<!-- Summary Table -->
<div class="summary-table-container">
  <table class="summary-table">
    <thead>
      <tr>
        <th>Total Users</th>
        <th>Total Booked Venues</th>
        <th>Total Revenue</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>${totalUsers}</td>
        <td>${totalBookedVenues}</td>
        <td>${totalRevenue}</td>
      </tr>
    </tbody>
  </table>
</div>


<!-- Main Content -->
<div class="admin-content">
  <div class="section">

    <c:if test="${not empty error}">
      <p style="color: red; text-align: center;">${error}</p>
    </c:if>

    <table>
      <thead>
        <tr>
          <th>User ID</th>
          <th>Full Name</th>
          <th>Username</th>
          <th>Email</th>
          <th>Contact</th>
          <th>User Role</th>
          <th>Password</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <c:choose>
          <c:when test="${not empty userList}">
            <c:forEach var="user" items="${userList}">
              <tr>
                <td>${user.userId}</td>
                <td>${user.fullName}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.contact}</td>
                <td>${user.userRole}</td>
                <td>${user.password}</td>
                <td>
                  <form action="${pageContext.request.contextPath}/EditUser" method="get" style="display:inline;">
                    <input type="hidden" name="userId" value="${user.userId}" />
                    <input type="hidden" name="source" value="admin" />
                    <button type="submit" class="edit">Edit</button>
                  </form>

                  <form action="${pageContext.request.contextPath}/DeleteUser" method="post" style="display:inline; margin-left: 5px;">
                    <input type="hidden" name="userId" value="${user.userId}" />
                    <button type="submit" class="delete" onclick="return confirm('Are you sure you want to delete this user?');">Delete</button>
                  </form>
                </td>
              </tr>
            </c:forEach>
          </c:when>
          <c:otherwise>
            <tr><td colspan="8" style="text-align: center;">No users found.</td></tr>
          </c:otherwise>
        </c:choose>
      </tbody>
    </table>
  </div>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>
