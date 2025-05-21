<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit User</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Edit-Profile.css"/>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet"/>
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="profile-container">
  <div class="profile-box">
    <h2>Edit User</h2>

    <form action="${pageContext.request.contextPath}/EditUser" method="POST" class="edit-profile-form">
      <input type="hidden" name="userId" value="${user.userId}" />
      <input type="hidden" name="source" value="${param.source}" />

      <div class="form-group">
        <label for="full-name">Full Name</label>
        <input type="text" id="full-name" name="fullName" value="${user.fullName}" required />
      </div>

      <div class="form-group">
        <label for="username">Username</label>
        <input type="text" id="username" name="username" value="${user.username}" required />
      </div>

      <div class="form-group">
        <label for="email">Email</label>
        <input type="email" id="email" name="email" value="${user.email}" required />
      </div>

      <div class="form-group">
        <label for="phone">Phone</label>
        <input type="tel" id="phone" name="contact" value="${user.contact}" required />
      </div>

      <div class="form-group">
        <label for="password">Password</label>
        <input type="text" id="password" name="password" value="${user.password}" required />
      </div>

      <div class="form-actions">
        <button type="submit" class="btn-save">Save Changes</button>

        <c:choose>
          <c:when test="${param.source eq 'profile'}">
            <a href="${pageContext.request.contextPath}/Profile"><button type="button" class="btn-cancel">Cancel</button></a>
          </c:when>
          <c:otherwise>
            <a href="${pageContext.request.contextPath}/Admin"><button type="button" class="btn-cancel">Cancel</button></a>
          </c:otherwise>
        </c:choose>
      </div>
    </form>
  </div>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>
