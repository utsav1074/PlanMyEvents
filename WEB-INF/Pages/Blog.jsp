<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Blog.css"/>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet"/>
</head>
<body>
<jsp:include page="header.jsp"/>

	    <!-- Blog Heading -->
    <div class="middle-section">
      <h1>Our Blog Post</h1>
    </div>

    <!-- Blog Posts -->
    <div class="first-container">
      <div class="image1">
        <img class="firstimage" src="${pageContext.request.contextPath}/images/wedding.png" />
      </div>
      <div class="heading1">Event Decor Trends in Nepal for 2025</div>
      <div class="heading2">FEB 23, 2025</div>
      <div class="heading3">
        <p>
          Planning a wedding in 2025? Whether you're dreaming of a traditional
          celebration or a modern fairytale, Plan My Event is here to keep you
          updated with the latest wedding decor trends in Nepal.
        </p>
      </div>
      <div class="button1">
        <button>Read More</button>
      </div>
    </div>

    <div class="first-container">
      <div class="image2">
        <img class="secondimage" src="${pageContext.request.contextPath}/images/cake.jpg" />
      </div>
      <div class="heading1">Best Birthday Cake Designers in Nepal</div>
      <div class="heading2">FEB 29, 2025</div>
      <div class="heading3">
        <p>
          Looking for the perfect cake to match your birthday theme? At Plan My
          Event, we collaborate with some of Nepal’s top cake artists who create
          everything from elegant floral tiers to fun cartoon-themed designs.
        </p>
      </div>
      <div class="button1">
        <button>Read More</button>
      </div>
    </div>

    <div class="first-container">
      <div class="image3">
        <img class="thirdimage" src="${pageContext.request.contextPath}/images/last.png" />
      </div>
      <div class="heading1">
        Why Hiring an Event Planner in Nepal Saves Time & Money?
      </div>
      <div class="heading2">FEB 29, 2025</div>
      <div class="heading3">
        <p>
          Planning an event in Nepal can be both exciting and overwhelming. Explore
          the benefits of hiring an event planner and make your next event a seamless
          success!
        </p>
      </div>
      <div class="button2">
        <button>Read More</button>
      </div>
    </div>

    <div class="first-container">
      <div class="image4">
        <img class="fourthimage" src="${pageContext.request.contextPath}/images/tradition'.png" />
      </div>
      <div class="heading1">Cultural Wedding Traditions in Nepal</div>
      <div class="heading2">FEB 29, 2025</div>
      <div class="heading3">
        <p>
          Nepal is a land rich in diversity and culture, and its wedding traditions
          reflect this beautiful tapestry. From the vibrant attire to the intricate
          rituals, each wedding is a unique celebration of heritage.
        </p>
      </div>
      <div class="button1">
        <button>Read More</button>
      </div>
    </div>

    <!-- Call to Action -->
    <section class="cta-section">
      <h2>Write your special wishes. We love to hear from all of you...</h2>
      <a href= "${pageContext.request.contextPath}/Aboutus">  <button class="cta-button">GET IN TOUCH</button>  </a>
    </section>

	<jsp:include page="footer.jsp"/>
	
</body>
</html>