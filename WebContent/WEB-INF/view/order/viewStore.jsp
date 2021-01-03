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
<title>EJ's Pizza Store</title>
</head>
<body>
<t:navbar />

<div class="container mt-5">
	<h2 class="text-center">Store</h2>
	<br />
	
	<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
	  <c:set value="active" var="active" />
	  <c:forEach items="${stores }" var="store">
		  <li class="nav-item" role="presentation">
		    <a class="nav-link ${active }" id="pills-${store.name }-tab" data-toggle="pill" href="#pills-${store.name }" role="tab" aria-controls="pills-${store.name }" aria-selected="false">
		    	<small>${store.name }</small>
		    </a>
		  </li>
		  <c:remove var="active" />
	  </c:forEach>
	</ul>
	<div class="tab-content" id="pills-tabContent">
	  <c:set value="show active" var="active" />
	  <c:forEach items="${stores }" var="store">
		  <div class="tab-pane fade ${active }" id="pills-${store.name }" role="tabpanel" aria-labelledby="pills-${store.name }-tab">
		  		
		  	<div class="card text-center">
			  <div class="card-header">EJ's Pizza Store</div>
			  <div class="card-body">
			    <h5 class="card-title">${store.name }</h5>
			    <p class="card-text">location : ${store.loc }</p>
			    <%-- 지도 첨부 --%>
			  </div>
			  <div class="card-footer text-muted">Contact : ${store.number }</div>
			</div>
		  </div>
		  
		  <c:remove var="active" />
	  </c:forEach>	
	</div>
	
	
</div>
</body>
</html>