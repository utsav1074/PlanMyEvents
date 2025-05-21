<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin - Booking Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Admin-Booking.css"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet"/>
</head>
<body>

<jsp:include page="header.jsp"/>

<!-- Admin Navbar -->
<div class="admin-navbar">
    <div class="brand">Admin Dashboard</div>
    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/Admin">User Management</a>
        <a href="${pageContext.request.contextPath}/Admin-Booking" class="active">Booking Management</a>
    </div>
</div>

<!-- Main Content -->
<div class="admin-content">
    <div class="section wide-table">
        <table>
            <thead>
                <tr>
                    <th>Booking ID</th>
                    <th>Full Name</th>
                    <th>Venue</th>
                    <th>Booking Date</th>
                    <th>Food Package</th>
                    <th>Decoration</th>
                    <th>Total People</th>
                    <th>Total Price</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="b" items="${allBookings}">
                    <tr>
                        <td>${b.bookingId}</td>
                        <td>${b.fullName}</td>
                        <td>${b.venueName}</td>
                        <td>${b.bookingDate}</td>
                        <td><c:out value="${b.foodSelected != null ? b.foodSelected : 'None'}"/></td>
                        <td><c:out value="${b.decorationSelected != null ? b.decorationSelected : 'None'}"/></td>
                        <td>${b.totalGuests}</td>
                        <td>${b.totalAmount}</td>
                        <td>
                            <c:choose>
                                <c:when test="${b.bookingStatus eq 'Booked'}">
                                    <form action="${pageContext.request.contextPath}/CancelBooking" method="post">
                                        <input type="hidden" name="bookingId" value="${b.bookingId}" />
                                        <button type="submit" onclick="return confirm('Are you sure you want to cancel this booking?');">Cancel</button>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <span style="color: gray;">${b.bookingStatus}</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>
