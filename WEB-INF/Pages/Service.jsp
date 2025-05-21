<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.time.LocalDate" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String today = LocalDate.now().toString();
    request.setAttribute("today", today);
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Book Venue</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Service.css"/>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet"/>
</head>
<body>

<jsp:include page="header.jsp"/>

<c:if test="${not proceedToAddOns}">
<section class="services">
  <h1>Choose Your Venue</h1>
  <p>Select a venue and customize with add-ons like our exclusive Food Package and Decoration services.</p>

  <c:if test="${not empty success}">
    <p id="MessageTimer" style="color: green; text-align: center; font-weight: bold;">${success}</p>
  </c:if>
  <c:if test="${not empty error}">
    <p id="MessageTimer" style="color: red; text-align: center; font-weight: bold;">${error}</p>
  </c:if>

  <div class="venues-container">
  
    <!-- Venue 1 -->
<div class="venue-card" id="venue1">
  <img src="${pageContext.request.contextPath}/images/photo1.jpg" alt="Grand Ballroom" class="venue-image" />
  <h2>Grand Ballroom</h2>
  <p class="description">An elegant space perfect for large events and celebrations.</p>
  <ul class="details">
    <li><strong>Price:</strong> $2,500/day</li>
    <li><strong>Capacity:</strong> 350 Guests</li>
  </ul>
  <button class="select-btn" onclick="selectVenue(1)">Select Venue</button>
</div>

<!-- Venue 2 -->
<div class="venue-card" id="venue2">
  <img src="${pageContext.request.contextPath}/images/photo2.jpg" alt="Garden Pavilion" class="venue-image" />
  <h2>Garden Pavilion</h2>
  <p class="description">Outdoor venue with lush greenery for a memorable experience.</p>
  <ul class="details">
    <li><strong>Price:</strong> $1,800/day</li>
    <li><strong>Capacity:</strong> 200 Guests</li>
  </ul>
  <button class="select-btn" onclick="selectVenue(2)">Select Venue</button>
</div>

<!-- Venue 3 -->
<div class="venue-card" id="venue3">
  <img src="${pageContext.request.contextPath}/images/photo1.jpg" alt="Conference Hall" class="venue-image" />
  <h2>Conference Hall</h2>
  <p class="description">Ideal for corporate events and conferences.</p>
  <ul class="details">
    <li><strong>Price:</strong> $2,000/day</li>
    <li><strong>Capacity:</strong> 150 Guests</li>
  </ul>
  <button class="select-btn" onclick="selectVenue(3)">Select Venue</button>
</div>

<!-- Venue 4 -->
<div class="venue-card" id="venue4">
  <img src="${pageContext.request.contextPath}/images/photo1.jpg" alt="Luxury Villa" class="venue-image" />
  <h2>Luxury Villa</h2>
  <p class="description">A private and luxurious setting for intimate gatherings.</p>
  <ul class="details">
    <li><strong>Price:</strong> $3,000/day</li>
    <li><strong>Capacity:</strong> 50 Guests</li>
  </ul>
  <button class="select-btn" onclick="selectVenue(4)">Select Venue</button>
</div>

<!-- Venue 5 -->
<div class="venue-card" id="venue5">
  <img src="${pageContext.request.contextPath}/images/photo2.jpg" alt="Rooftop Garden" class="venue-image" />
  <h2>Rooftop Garden</h2>
  <p class="description">A breathtaking venue with panoramic city views.</p>
  <ul class="details">
    <li><strong>Price:</strong> $2,200/day</li>
    <li><strong>Capacity:</strong> 120 Guests</li>
  </ul>
  <button class="select-btn" onclick="selectVenue(5)">Select Venue</button>
</div>

<!-- Venue 6 -->
<div class="venue-card" id="venue6">
  <img src="${pageContext.request.contextPath}/images/photo1.jpg" alt="Lakeview Hall" class="venue-image" />
  <h2>Lakeview Hall</h2>
  <p class="description">A stunning venue with serene lake views for special occasions.</p>
  <ul class="details">
    <li><strong>Price:</strong> $2,800/day</li>
    <li><strong>Capacity:</strong> 250 Guests</li>
  </ul>
  <button class="select-btn" onclick="selectVenue(6)">Select Venue</button>
</div>

<!-- Venue 7 -->
<div class="venue-card" id="venue7">
  <img src="${pageContext.request.contextPath}/images/photo1.jpg" alt="Beachfront Resort" class="venue-image" />
  <h2>Beachfront Resort</h2>
  <p class="description">A perfect coastal venue with a view of the ocean for your special day.</p>
  <ul class="details">
    <li><strong>Price:</strong> $4,000/day</li>
    <li><strong>Capacity:</strong> 500 Guests</li>
  </ul>
  <button class="select-btn" onclick="selectVenue(7)">Select Venue</button>
</div>

<!-- Venue 8 -->
<div class="venue-card" id="venue8">
  <img src="${pageContext.request.contextPath}/images/photo2.jpg" alt="Mountain Lodge" class="venue-image" />
  <h2>Mountain Lodge</h2>
  <p class="description">An intimate venue surrounded by nature and mountains.</p>
  <ul class="details">
    <li><strong>Price:</strong> $2,500/day</li>
    <li><strong>Capacity:</strong> 100 Guests</li>
  </ul>
  <button class="select-btn" onclick="selectVenue(8)">Select Venue</button>
</div>
    
  </div>
</section>
</c:if>

<!-- STEP 1: Booking Date Popup -->
<div id="datePopup" class="popup-overlay" style="<c:choose>
    <c:when test='${showDatePopup}'>display: flex;</c:when>
    <c:otherwise>display: none;</c:otherwise>
</c:choose>">
  <div class="popup-content">
    <h2>Select Booking Date</h2>

    <form method="post" action="${pageContext.request.contextPath}/Service">
      <input type="hidden" name="venueId" id="popupVenueId" value="${venueId}" />
      <br>
      <label for="bookingDate">Booking Date:</label>
      <input type="date" name="bookingDate" id="popupBookingDate" min="${today}" value="${bookingDate}" required />

      <label for="guestCount">No. of Guests:</label>
      <input type="number" name="guestCount" id="popupGuestCount" value="${guestCount}" min="1" required/>

      <br/>
      
      <c:if test="${not empty bookingError}">
        <p id="MessageTimer" style="color: red; font-weight: bold; text-align: center;">${bookingError}</p>
      </c:if>

      <br/>
      
      <button type="submit" class="continue-btn">Next</button>
      <button type="button" onclick="document.getElementById('datePopup').style.display='none'">Cancel</button>
    </form>
  </div>
</div>

<!-- STEP 2: Add-Ons Section -->
<div id="addonsSection" style="<c:choose>
    <c:when test='${proceedToAddOns}'>display: block;</c:when>
    <c:otherwise>display: none;</c:otherwise>
</c:choose>">
  <section class="addons">
    <h2>Select Add-Ons</h2>
    
    
<form method="post" action="${pageContext.request.contextPath}/Service">

  <!-- Hidden flag to indicate final step -->
  <input type="hidden" name="finalSubmission" value="true" />

  <!-- Preserve booking details -->
  <input type="hidden" name="venueId" value="${venueId}" />
  <input type="hidden" name="bookingDate" value="${bookingDate}" />
  <input type="hidden" name="guestCount" value="${guestCount}" />

  <div class="addons-container">
  
    <!-- Food Card -->
    <div class="addon-card">
      <img src="${pageContext.request.contextPath}/images/photo1.jpg" alt="Food" class="addon-image" />
      <h3>Food Package</h3>
      <p>Select a food package suited for your guests.</p>

      <div class="food-package-options">
        <input type="radio" id="silver-package" name="food-package" value="1"/>
        <label for="silver-package"><strong>Silver Package</strong></label>
        <ul>
          <li>Standard menu with vegetarian and non-vegetarian options</li>
          <li>Price: $30/person</li>
        </ul>
      </div>

      <div class="food-package-options">
        <input type="radio" id="gold-package" name="food-package" value="2"/>
        <label for="gold-package"><strong>Gold Package</strong></label>
        <ul>
          <li>Premium menu with multi-cuisine buffet and dessert station</li>
          <li>Price: $50/person</li>
        </ul>
      </div>
    </div>

    <!-- Decoration Card -->
    <div class="addon-card">
      <img src="${pageContext.request.contextPath}/images/photo2.jpg" alt="Decoration" class="addon-image" />
      <h3>Decoration</h3>
      <p>Select your preferred decoration style for the venue.</p>

      <div class="decoration-options">
        <input type="radio" id="basic-decoration" name="decoration" value="1" />
        <label for="basic-decoration"><strong>Basic Decoration</strong></label>
        <ul>
          <li>Elegant white linens, Simple floral centerpieces</li>
          <li>Price: $500</li>
        </ul>
      </div>

      <div class="decoration-options">
        <input type="radio" id="premium-decoration" name="decoration" value="2" />
        <label for="premium-decoration"><strong>Premium Decoration</strong></label>
        <ul>
          <li>Luxury drapes, Customized floral arrangements, Light installations</li>
          <li>Price: $1,000</li>
        </ul>
      </div>
    </div>

  </div>
  
  <br>
  <!-- Confirm Button -->
  <div>
    <button style="width:160px;" type="submit" class="continue-btn">Confirm Booking</button>
  </div>

</form>

  </section>
</div>

<jsp:include page="footer.jsp"/>

<script>
  function selectVenue(venueId) {
    document.getElementById("popupVenueId").value = venueId;
    document.getElementById("popupBookingDate").value = "";
    document.getElementById("popupGuestCount").value = "";
    document.getElementById("datePopup").style.display = "flex";
  }

  setTimeout(function() {
    const MessageTimer = document.getElementById('MessageTimer');
    if (MessageTimer) {
      MessageTimer.style.display = 'none';
    }
  }, 2500);
</script>

</body>
</html>
