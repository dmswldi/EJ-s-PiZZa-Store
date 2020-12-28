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
	<h2 class="text-center">Customer Center</h2>
	<small class="form-text text-muted text-center">We are always open to you.</small>
	<br />
	<table class="table table-hover">
	  <thead>
	    <tr>
	      <th scope="col">No.</th>
	      <th scope="col">Category</th>
	      <th scope="col">Title</th>
	      <th scope="col">Id</th>
	      <th scope="col">Status</th>
	    </tr>
	  </thead>
	  
	  <tbody>
	  	<c:if test="${not empty page }">
	  	  <c:forEach items="${page.list }" var="post">
		    <tr>
		      <th scope="row">${post.id }</th>
		      <td>${post.category }</td>
		      <td>${post.title }</td>
		      <td>${post.customerId }</td>
		      <td>${post.status }</td>
		    </tr>
	  	  </c:forEach>
	  	</c:if>
	  </tbody>
	</table>
	<br />
	<a class="btn btn-primary float-right" href="${root }/cs/write.do">글쓰기</a>
	<nav>
	  <ul class="pagination d-flex justify-content-center">
	    <li class="page-item"><a class="page-link" href="#">Previous</a></li>
	    <c:if test="${not empty page }">
	      <c:forEach begin="1" end="${post.total }" var="pageNo">
	    	<li class="page-item"><a class="page-link" href="#">${pageNo }</a></li>
	      </c:forEach>
	    </c:if>
	    
	    <li class="page-item"><a class="page-link" href="#">Next</a></li>
	  </ul>
	</nav>
</div>
</body>
</html>