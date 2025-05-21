<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Register.css"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet"/>
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="form-container">
    <div class="form-box">
        <h2>Register</h2>

        <%-- ✅ Fixed form action to point to Register servlet --%>
        <form action="${pageContext.request.contextPath}/Register" method="POST">
            <div class="input-group">
                <label for="fullname">Full Name</label>
                <input type="text" id="fullname" name="fullname" placeholder="Enter your full name" required>
            </div>

            <div class="input-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="Enter your email" required />
            </div>

            <div class="input-group">
                <label for="contacts">Contact</label>
                <input type="text" id="phonenum" name="contacts" placeholder="Enter your phone number" required>
            </div>

            <div class="input-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" placeholder="Choose a username" required />
            </div>

            <div class="input-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Create a password" required />
            </div>

            <div class="form-footer">
                <button type="submit" class="btn">Register</button>
                <p>Already have an account? <a href="${pageContext.request.contextPath}/Login">Login here</a></p>
            </div>
        </form>
    </div>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>
