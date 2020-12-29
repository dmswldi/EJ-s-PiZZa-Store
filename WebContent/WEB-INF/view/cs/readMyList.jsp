<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="cscenter.model.PostPage"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<link href="${root }/css/readPostList.css" rel="stylesheet" type="text/css">
<title>EJ's Pizza Store</title>
</head>
<body>
<t:navbar />

<div class="container mt-5">
	<h2 class="text-center">Customer Center</h2>
	<small class="form-text text-muted text-center">Hello, ${post.customerId }</small>
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
	  	<c:if test="${empty page }">
	  		<tr>
	  		  <td colspan="5"><small class="text-muted">게시글이 존재하지 않습니다.</small></td>
	  		</tr>
	  	</c:if>
  	    <c:forEach items="${page.list }" var="post">
	      <tr class="modifiable" onclick="location.href='${root }/cs/read.do?id=${post.id }&pageNo=${page.pageNum }'">
	        <th scope="row">${post.id }</th>
	        <td>${post.category }</td>
	        <td>${post.title }</td>
	        <td>${post.customerId }</td>
	        <td>${post.status }</td>
	      </tr>
  	    </c:forEach>
	  </tbody>
	</table>
	<br />
	<a class="btn btn-primary float-right" href="${root }/cs/write.do">글쓰기</a>
	<nav>
	  <ul class="pagination d-flex justify-content-center">
	  	<c:if test="${page.startPage > page.size}">
	      <li class="page-item"><a class="page-link" href="${root }/cs/mylist.do?pageNo=${page.startPage - page.size }">Previous</a></li>
	    </c:if>
	    <c:forEach begin="${page.startPage }" end="${page.endPage }" varStatus="status">
		  <c:if test="${page.pageNum eq status.index }">
			<c:set var="active" value="active" />
		  </c:if>
	      <c:if test="${status.index <= page.totalPages }">
	    	<li class="page-item ${active }"><a class="page-link" href="${root }/cs/mylist.do?pageNo=${status.index }">${status.index }</a></li>
	      </c:if>
	      <c:remove var="active" />
	    </c:forEach>
	    <c:if test="${page.endPage < page.totalPages}">
  	      <li class="page-item"><a class="page-link" href="${root }/cs/mylist.do?pageNo=${page.endPage + 1 }">Next</a></li>
	    </c:if>
	  </ul>
	</nav>
</div>
</body>
</html>