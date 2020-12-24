<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<title>EJ's Pizza Store</title>
</head>
<body>
<t:navbar />
<div class="container mt-5">
	<div class="row">
		<div class="col-3"></div>
		<div class="col-6">
			<h2 class="text-center">개인 정보 수정</h2>
			<br />
			<form action="${root }/modify.do" method="post">
			  <div class="form-group">
			    <label for="id">ID</label>
			    <input type="text" class="form-control" id="id" name="id" value="${sessionScope.user.id }" disabled>
			  </div>
			  <div class="form-group">
			    <label for="name">Name</label>
			    <input type="text" class="form-control" id="name" name="name" value="${sessionScope.user.name }" disabled>
			  </div>
			  <div class="form-group">
			    <label for="curPassword">Current Password</label>
			    <input type="password" class="form-control" id="curPassword" name="curPassword" pattern="(?=.*\d)(?=.*[A-Za-z]).{8,}" required>
				<c:if test="${errors.pwNotMatch }">
				  <small class="form-text text-danger">Passwords is wrong.</small>
				</c:if>
			  </div>
			  <div class="form-group">
			    <label for="newPassword">New Password</label>
			    <input type="password" class="form-control" id="newPassword" name="newPassword" pattern="(?=.*\d)(?=.*[A-Za-z]).{8,}" required>
			    <small class="form-text text-muted">Must contain one number and at least 8 characters.</small>
			  </div>
			  <div class="form-group">
			    <label for="passwordCheck">PasswordCheck</label>
			    <input type="password" class="form-control" id="passwordCheck" name="passwordCheck" pattern="(?=.*\d)(?=.*[A-Za-z]).{8,}" required>
			    <c:if test="${errors.passwordNotEqual }">
				    <small class="form-text text-danger">Passwords don't match.</small>
			    </c:if>
			  </div>
			  <div class="form-group">
			    <label for="phone">Phone</label>
			    <div class="form-row">
			      <div class="col">
		        	<select class="form-control" id="phone" name="phone" >
		        	  <option selected>010</option>
		              <option>011</option>
		            </select>
			      </div>
			      <div class="input-group-prepend">
				    <span class="input-group-text">-</span>
				  </div>
				  <c:set var="length" value="${fn:length(sessionScope.user.phone)}"/>
			      <div class="col">
			        <input class="form-control" type="text" name="phone" value="${fn:substring(sessionScope.user.phone, 3, length-4)}" pattern="(?=.*\d).{3,4}" required>
			      </div>
			      <div class="input-group-prepend">
				    <span class="input-group-text">-</span>
				  </div>
			      <div class="col">
			        <input class="form-control" type="text" name="phone" value="${fn:substring(sessionScope.user.phone, length-4, length)}" pattern="(?=.*\d).{4}" required>
			      </div>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="address">Address</label> <%-- 주소 검색...ㅎㅎ --%>
			    <input type="text" class="form-control" id="address" name="address" value="${sessionScope.user.address }">
			  </div>
			  <button type="submit" class="btn btn-primary float-right">Done</button>
			</form>
		</div>
	</div>
</div>
</body>
</html>