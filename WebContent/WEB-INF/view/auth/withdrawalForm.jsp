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
			<h2 class="text-center">회원탈퇴</h2>
			<br />
			<form action="${root }/withdrawal.do" method="post">
			  <div class="form-group">
			    <label for="password">Password</label>
			    <input type="password" class="form-control" id="password" name="password" required>
			  </div>
			  <c:if test="${errors.pwNotMatch }">
			  	<small class="form-text text-danger">Passwords is wrong.</small>
			  </c:if>
			 <button type="submit" class="btn btn-primary float-right">Withdrawal</button>
			 </form>
		</div>
	</div>
</div>
</body>
</html>