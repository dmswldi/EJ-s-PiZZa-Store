<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<h2 class="text-center">로그인</h2>
			<br />
			<form action="${root }/login.do" method="post">
			  <div class="form-group">
			    <label for="id">ID</label>
			    <input type="text" class="form-control" id="id" name="id" value="${param.id }" required>
			    <c:if test="${errors.idNotExist }">
			    	<small class="form-text text-danger">This ID does not exist.</small>
			    </c:if>
			  </div>
			  <div class="form-group">
			    <label for="password">Password</label>
			    <input type="password" class="form-control" id="password" name="password" required>
			  </div>
			  <c:if test="${errors.pwNotMatch }">
			    	<small class="form-text text-danger">Passwords is wrong.</small>
			  </c:if>
			  <div class="form-group form-check"> <%-- pass...... 세션, 쿠키, 쿠키 경로/... 세션 시간 한달 정도 --%>
			    <label class="form-check-label">
			      <input class="form-check-input" type="checkbox"> Remember me
			    </label>
			  </div>
			  <button type="submit" class="btn btn-primary float-right">Sign in</button>
			</form>  			  			
		</div>
	</div>
</div>
</body>
</html>