<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Profile</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Profile.css"/>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet"/>
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="profile-container">
  <div class="profile-box">
    <h1>Your Profile</h1>

    <div class="profile-main">
      <!-- Left: Profile Image + Upload -->
      <div class="profile-image">
        <div class="image-box">
          <c:choose>
            <c:when test="${not empty user.imagePath}">
              <img src="${pageContext.request.contextPath}/Profile/${user.imagePath}" alt="Profile Picture" />
            </c:when>
            <c:otherwise>
              <img src="${pageContext.request.contextPath}/Profile/default.png" alt="Default Profile Picture" />
            </c:otherwise>
          </c:choose>
        </div>

        <form action="${pageContext.request.contextPath}/Profile" method="post" enctype="multipart/form-data">
          <input type="file" name="profileImage" accept="image/*" required />
          <button type="submit">Upload</button>
        </form>

        <c:if test="${not empty errorMessage}">
          <p class="error-message">${errorMessage}</p>
        </c:if>
      </div>

      <!-- Right: Personal Info -->
      <div class="profile-info">
        <p><span class="label">Full Name:</span> ${user.fullName}</p>
        <p><span class="label">Username:</span> ${user.username}</p>
        <p><span class="label">Email:</span> ${user.email}</p>
        <p><span class="label">Phone:</span> ${user.contact}</p>
      </div>
    </div>
    
    <div class="profile-footer">
  <form action="${pageContext.request.contextPath}/EditUser" method="get" style="display:inline;">
  	<input type="hidden" name="userId" value="${sessionScope.userId}" />
  	<input type="hidden" name="source" value="profile" />
  	<button type="submit">Edit Profile</button>
  </form>
  

  <form action="${pageContext.request.contextPath}/EditUser" method="get" style="display:inline;">
  	<input type="hidden" name="userId" value="${sessionScope.userId}" />
  	<input type="hidden" name="source" value="profile" />
    <button type="submit">Change Password</button>
  </form>
</div>

    <!-- Booking History Section -->
    <div class="booking-history">
      <h3>Your Bookings</h3>
      <table>
        <thead>
          <tr>
            <th>Booking ID</th>
            <th>Venue</th>
            <th>Booking Status</th>
            <th>Add-Ons</th>
            <th>Total Amount</th>
            <th>Booking Date</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          <c:choose>
            <c:when test="${not empty bookingHistory}">
              <c:forEach var="booking" items="${bookingHistory}">
                <tr>
                  <td>${booking.bookingId}</td>
                  <td>${booking.venueName}</td>
                  <td>${booking.bookingStatus}</td>
                  <td>
                    <c:choose>
                      <c:when test="${not empty booking.foodSelected or not empty booking.decorationSelected}">
                        ${booking.foodSelected} / ${booking.decorationSelected}
                      </c:when>
                      <c:otherwise>No Add-ons</c:otherwise>
                    </c:choose>
                  </td>
                  <td>$${booking.totalAmount}</td>
                  <td>${booking.bookingDate}</td>
                  <td>
                    <c:if test="${booking.bookingStatus != 'Cancelled'}">
                      <form action="${pageContext.request.contextPath}/CancelBooking" method="post"
                         onsubmit="return confirm('Are you sure you want to cancel this booking?');" ">
                      	<input type="hidden" name="bookingId" value="${booking.bookingId}" />
                        <input type="hidden" name="source" value="profile" />
                        <button type="submit" class="cancel-btn">Cancel</button>
                        </form>
                      
                    </c:if>
                    <c:if test="${booking.bookingStatus == 'Cancelled'}">
                      <span style="color: gray;">Cancelled</span>
                    </c:if>
                  </td>
                </tr>
              </c:forEach>
            </c:when>
            <c:otherwise>
              <tr>
                <td colspan="7" style="text-align: center;">No bookings found.</td>
              </tr>
            </c:otherwise>
          </c:choose>
        </tbody>
      </table>
    </div>

  </div>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>
