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
			<h2 class="text-center">Customer Center</h2>
			<small class="form-text text-muted text-center">We are always open to you.</small>
			<br />
			<form action="${root }/cs/write.do" method="post">
			  <div class="form-group">
			  	<label for="category">종류</label>
			  	<select class="form-control" name="category" id="category" required>
			  		<option value="customer">회원 관련</option>
			  		<option value="order" selected>주문 관련</option>
			  		<option value="etc">기타</option>
			  	</select>
			  </div>
			  <div class="form-group">
			    <label for="title">title</label>
			    <input type="text" class="form-control" id="title" name="title" value="${param.title }" required>
			  </div>
			  <div class="form-group">
			    <label for="content">content</label>
			    <textarea class="form-control" id="content" name="content" rows="18" required>${param.title }</textarea>
			  </div>
			  <button type="submit" class="btn btn-primary float-right">Save</button>
			</form>
		</div>
	</div>
</div>
</body>
</html>