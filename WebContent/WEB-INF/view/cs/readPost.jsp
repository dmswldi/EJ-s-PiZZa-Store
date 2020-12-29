<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script>
var root = "${root }";
var id = "${post.id }";
var pageNo = "${pageNum }";
</script>
<script type="text/javascript" src="${root }/script/readPost.js"></script>
<link href="${root }/css/readPost.css" rel="stylesheet" type="text/css">
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
			<form action="${root }/cs/modify.do" method="post">
			  <input type="text" name="id" value="${post.id }" hidden=true>
			  <input type="text" name="pageNo" value="${pageNo }" hidden=true>
			  <div class="form-group">
			    <fmt:formatDate var="date" value="${post.date }" pattern="yyyy-MM-dd HH:mm"/>
			    <small class="form-text text-muted">${post.customerId }</small>
			    <small class="form-text text-muted float-right">${date }</small>
			  </div>
			  <div class="form-group">
			  	<label for="category">종류</label>
			  	<select class="form-control" name="category" id="category" disabled required>
			  		<option value="customer">회원 관련</option>
			  		<option value="order" selected>주문 관련</option>
			  		<option value="etc">기타</option>
			  	</select>
			  </div>
			  <div class="form-group">
			    <label for="title">title</label>
			    <input type="text" class="form-control" id="title" name="title" value="${post.title }" readonly required>
			  </div>
			  <div class="form-group">
			    <label for="content">content</label>
			    <textarea class="form-control" id="content" name="content" rows="15" readonly required>${post.content }</textarea>
			  </div>
			  <button type="submit" class="btn btn-primary float-right" id="submit_btn">Save</button>
			</form>
			<button class="btn btn-danger float-right mr-1" id="cancel_btn">Cancel</button>
			<c:if test="${post.customerId eq sessionScope.user.id }">
			  <button class="btn btn-primary float-right" id="modify_btn">Modify</button>
			  <button class="btn btn-danger float-right mr-1" id="remove_btn">Remove</button>
			</c:if>
			<button class="btn btn-secondary" id="list">글 목록</button>
			
			<%-- comment --%>
			<form action="" method="post">
			  <div class="form-group">
			    <label for="comments" style="display:inline">comments</label>
			    <input type="text" class="form-control" id="comments" name="comments" required  style="display:inline">
			    <button type="submit" class="btn btn-primary float-right" id="submit_btn"  style="display:inline">Save</button>
			  </div>
			</form>
		</div>
	</div>
</div>
</body>
</html>