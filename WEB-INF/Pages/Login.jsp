<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Login.css"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet"/>
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="form-container">
    <div class="form-box">
        <h2>Login</h2>
        <form action="${pageContext.request.contextPath}/Login" method="POST">
            <div class="input-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" placeholder="Enter your username" required />
            </div>

            <div class="input-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Enter your password" required />
            </div>

            <div class="form-footer">
                <button type="submit" class="btn">Login</button>
                <p style="color: red; text-align: center;">
                    ${errorMessage != null ? errorMessage : ""}
                </p>
                <p>Don't have an account? <a href="${pageContext.request.contextPath}/Register">Register here</a></p>
            </div>
        </form>
    </div>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>
