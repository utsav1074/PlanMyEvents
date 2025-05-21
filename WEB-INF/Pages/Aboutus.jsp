<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Aboutus.css"/>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet"/>
</head>
<style>
  .Intro {
    position: relative;
    background-image: url(${pageContext.request.contextPath}/images/wedhand.jpg);
    background-size: contain;
    height: 60vh;
    display: flex;
    align-items: center;
    justify-content: center;
    text-align: center;
  }
</style>
<body>
	 <jsp:include page="header.jsp"/>

  <section class="Intro">
    <div class="overlay"></div>
  </section>

  <section class="AboutUs">
    <div class="Intro-content">
      <h1>ABOUT US</h1>
    </div>

    <div class="aboutus-container">
      <div class="image">
        <img src="${pageContext.request.contextPath}/images/download.jpeg" alt="us-img" />
      </div>
      <div class="aboutus-card">
        <h2>WHO ARE WE?</h2>
        <p>
          We are PlanMyEvent, a dedicated management system built to transform your special day into a seamless, stress-free celebration. With a passion for perfection and a love for love, we bring together the best of beautiful venues, delectable cuisine, and stunning decor services—all under one digital roof.
        </p>
      </div>
    </div>

    <div class="vision-container">
      <h3>OUR VISION</h3>
      <div class="vision-content">
        <p>
          To revolutionize wedding planning through a seamless all-in-one digital platform. Our vision is to turn stressful checklists into joyful milestones, making the journey to 'I do' as memorable and magical as the big day itself.
        </p>
      </div>

      <h4>OUR MISSION</h4>
      <div class="mission-content">
        <p>
          To empower couples by providing smart, organized, and stress-free planning solutions. We strive to simplify the entire wedding process, helping our users save time, stay in control, and enjoy every moment while bringing their dream celebration to life.
        </p>
      </div>
    </div>

    <div class="role-container">
      <div class="role-image">
        <img src="${pageContext.request.contextPath}/images/decor.jpeg" alt="Decoration image" />
      </div>
      <div class="role-card">
        <input id="ch" type="checkbox" />
        <h5>WHAT WE DO?</h5>
        <p>
          At PlanMyEvent, we make the wedding planning a joyful and effortless experience. We provide couples with an all-in-one digital platform that takes care of every detail—so they can focus on love, not logistics. From setting timelines and managing guest lists to keeping everything beautifully organized, our system is designed to guide them gently from the proposal to the big day...
        </p>
        <div class="role-content">
          <p>
            We also offer essential services like breathtaking venues, curated food packages, and elegant decor options—all in one place. With simplicity, clarity, and a touch of magic, we help couples bring their vision to life and celebrate their day with confidence, joy, and ease.
          </p>
          <label for="ch">Learn Less</label>
        </div>
        <label for="ch">Learn More</label>
      </div>
    </div>

    <h6 class="client-header">Love From Our Clients!</h6>
    <div class="client-container">
      <div class="card">
        <img src="${pageContext.request.contextPath}/images/client1.jpg" alt="image1" />
        <div class="card-content">
          <h7>Raj & Priya</h7>
          <p>
            "PlanMyEvent made our wedding stress-free! From venue selection to the final send-off, everything was flawless. The attention to detail was incredible, and we truly had the wedding of our dreams."
          </p>
        </div>
      </div>
      <div class="card">
        <img src="${pageContext.request.contextPath}/images/client2.jpg" alt="image1" />
        <div class="card-content">
          <h7>Sara & John</h7>
          <p>
            "We are amazed at how seamless the planning process was. The platform kept everything organized, and the team was always available to assist. Highly recommended!"
          </p>
        </div>
      </div>
      <div class="card">
        <img src="${pageContext.request.contextPath}/images/client4.jpg" alt="image1" />
        <div class="card-content">
          <h7>Hitesh & Shreya</h7>
          <p>
            "A fantastic service that took care of everything! The real-time updates and easy-to-use interface helped us stay on track. Thank you for making our wedding magical!"
          </p>
        </div>
      </div>
    </div>
  </section>

<jsp:include page="footer.jsp"/>

</body>
</html>