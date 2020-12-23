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
			<h2 class="text-center">회원가입</h2> <%-- id name pw phone address --%>
			<br />
			<form action="${root }/join.do" method="post">
			  <div class="form-group">
			    <label for="id">ID</label>
			    <input type="text" class="form-control" id="id" name="id" value="${param.id }" pattern="(?=.*\d)(?=.*[A-Za-z]).{8,}" required>
			    <small class="form-text text-muted">Please enter one number and at least 8 characters.</small>
			    <c:if test="${errors.duplicatedID }">
			    	<small class="form-text text-danger">This ID is already exist.</small>
			    </c:if>
			  </div>
			  <div class="form-group">
			    <label for="name">Name</label>
			    <input type="text" class="form-control" id="name" name="name" value="${param.name }" required>
			  </div>
			  <div class="form-group">
			    <label for="password">Password</label>
			    <input type="password" class="form-control" id="password" name="password" pattern="(?=.*\d)(?=.*[A-Za-z]).{8,}" required>
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
			      <div class="col">
			        <input class="form-control" type="text" name="phone" value="${paramValues.phone[1] }" pattern="(?=.*\d).{3,4}" required>
			      </div>
			      <div class="input-group-prepend">
				    <span class="input-group-text">-</span>
				  </div>
			      <div class="col">
			        <input class="form-control" type="text" name="phone" value="${paramValues.phone[2] }" pattern="(?=.*\d).{4}" required>
			      </div>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="address">Address</label> <%-- 주소 검색...ㅎㅎ --%>
			    <input type="text" class="form-control" id="address" name="address" value="${param.address }">
			  </div>
			  <button type="submit" class="btn btn-primary float-right">Join</button>
			</form>
		</div>
	</div>
</div>
</body>
</html>