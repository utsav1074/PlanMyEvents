<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Contact.css"/>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
</head>
<body>

<jsp:include page="header.jsp"/>

	   <div class="contact-container">
        <div class="contact-info">
            <h2 class="contact-heading">Contact</h2>
            <h3 class="contact-subheading">Get In Touch</h3>
            <p class="contact-text">If you have any questions or queries, feel free to write to us or visit us anytime.</p>
        </div>

        <div class="contact-body">
            <div class="contact-cards">
                <div class="contact-card">
                    <div class="contact-icon">
                        <i class="fas fa-mobile-alt"></i>
                    </div>
                    <h3>Phone No.</h3>
                    <div class="contact-details">
                        <p>Telephone: 01-4494842</p>
                        <p>Mobile: +977-9803679404</p>
                        <p>9851091010</p>
                    </div>
                </div>
                
                <div class="contact-card">
                    <div class="contact-icon">
                        <i class="fas fa-envelope-open"></i>
                    </div>
                    <h3>Email</h3>
                    <div class="contact-details">
                        <p>welcome@event.com</p>
                    </div>
                </div>
                
                <div class="contact-card">
                    <div class="contact-icon">
                        <i class="fas fa-map-marker-alt"></i>
                    </div>
                    <h3>Address</h3>
                    <div class="contact-details">
                        <p>Welcome Event Management Pvt. Ltd</p>
                        <p>Ghumti Kumari Marg, Mid-Baneshwor</p>
                        <p>Kathmandu</p>
                    </div>
                </div>
            </div>
        </div>

        <br><br><br>

        <div class="map">
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3532.5829497683176!2d85.34313120000003!3d27.699281999999993!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x39eb199b6b94ce27%3A0x40d1fb2a1a54d33d!2sGhumti%20Kumari%20Marg%2C%20Kathmandu%2044600!5e0!3m2!1sen!2snp!4v1743658559717!5m2!1sen!2snp" width="100%" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
        </div>
        
      </div>
      
      <jsp:include page="footer.jsp"/>
      
</body>
</html>