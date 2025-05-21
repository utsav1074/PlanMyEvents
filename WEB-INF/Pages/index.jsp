<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css"/>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet"/>
</head>
<body>

<jsp:include page="header.jsp"/>

  <section class="hero-section">
    <div class="image-container">
      <div class="slide slide-1 active first">
        <img src="${pageContext.request.contextPath}/images/photo1.jpg" alt="Event Image 1" class="hero-image" />
        <div class="overlay-box">
          <h1 class="overlay-heading">Get Married in Style</h1>
          <p class="overlay-subtext">Elegance & Continued Excellence to every Event</p>
          <a href="${pageContext.request.contextPath}/Service"> <button class="hero-button">PLAN YOUR EVENT</button> </a>
        </div>
      </div>

      <div class="slide slide-2">
        <img src="${pageContext.request.contextPath}/images/photo6.jpg" alt="Event Image 2" class="hero-image" />
        <div class="overlay-box">
          <h1 class="overlay-heading">Celebrate with Elegance</h1>
          <p class="overlay-subtext">Elegance & Continued Excellence to every Event</p>
          <a href="${pageContext.request.contextPath}/Service"> <button class="hero-button">PLAN YOU EVENT</button> </a>
        </div>
      </div>

      <div class="slide slide-3">
        <img src="${pageContext.request.contextPath}/images/photo3.jpg" alt="Event Image 3" class="hero-image" />
        <div class="overlay-box">
          <h1 class="overlay-heading">Memories that Last Forever</h1>
          <p class="overlay-subtext">Elegance & Continued Excellence to every Event</p>
          <a href="${pageContext.request.contextPath}/Service"> <button class="hero-button">PLAN YOU EVENT</button> </a>
        </div>
      </div>

      <button class="slide-button left" onclick="prevSlide()">&lt;</button>
      <button class="slide-button right" onclick="nextSlide()">&gt;</button>
    </div>
  </section>

  <section class="history-section">
    <div class="history-container">
      <div class="history-heading">
        <h4>About Us</h4>
        <h2>Our History</h2>
      </div>
      <div class="history-content">
        <p>
          Plan My Event is a full-fledged event planning and coordinating company in Nepal. 
          We provide one of the top solutions for corporate and social events such as product launch, 
          dealers/customer meets, press conference, meeting, seminar, wedding & other personal events in Nepal.
        </p>
        <a href="${pageContext.request.contextPath}/Aboutus">  <button class="read-more-btn">READ MORE</button>  </a>
      </div>
    </div>
  </section>

  <section class="culture-banner">
    <img src="${pageContext.request.contextPath}/images/photo9.jpg" alt="Traditional Wedding" class="culture-background" />
    <div class="culture-overlay">
      <h1>The Culture We Always Keep</h1>
    </div>
  </section>

  <section class="our-services">
    <h2 class="services-heading">Our Services</h2>
    <div class="services-list">
      <div class="service-item">
        <img src="${pageContext.request.contextPath}/images/photo1.jpg" alt="Venue">
        <p class="service-label">Venues</p>
      </div>
      <div class="service-item">
        <img src="${pageContext.request.contextPath}/images/photo2.jpg" alt="Food Package">
        <p class="service-label">Food Package</p>
      </div>
      <div class="service-item">
        <img src="${pageContext.request.contextPath}/images/photo3.jpg" alt="Decoration">
        <p class="service-label">Decoration</p>
      </div>
    </div>
    <a href="${pageContext.request.contextPath}/Service"> <button class="book-now-btn">Book Now</button> </a>
  </section>  
  
  

  <script>
    const slides = document.querySelectorAll('.slide');
    let currentIndex = 0;

    function showSlide(index) {
      slides.forEach((slide, i) => {
        slide.classList.remove('active');
        slide.querySelector('.overlay-box').classList.remove('show');
      });

      slides[index].classList.add('active');

      // Delay the overlay box appearance
      setTimeout(() => {
        slides[index].querySelector('.overlay-box').classList.add('show');
      }, 1500); // Matches image animation time
    }

    function nextSlide() {
      currentIndex = (currentIndex + 1) % slides.length;
      showSlide(currentIndex);
    }

    function prevSlide() {
      currentIndex = (currentIndex - 1 + slides.length) % slides.length;
      showSlide(currentIndex);
    }

    // Show overlay box for the first slide on page load
    window.addEventListener('DOMContentLoaded', () => {
      setTimeout(() => {
        slides[0].querySelector('.overlay-box').classList.add('show');
      }, 1500);
    });
  </script>

<jsp:include page="footer.jsp"/>

</body>
</html>