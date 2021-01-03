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
	<h2 class="text-center">Menu</h2>
	<br />
	
	<nav>
	  <div class="nav nav-tabs" id="nav-tab" role="tablist">
	    <a class="nav-link active" id="nav-all-tab" data-toggle="tab" href="#nav-all" role="tab" aria-controls="nav-all" aria-selected="true">All</a>
	    <a class="nav-link" id="nav-classic-tab" data-toggle="tab" href="#nav-classic" role="tab" aria-controls="nav-classic" aria-selected="false">Classic</a>
	    <a class="nav-link" id="nav-premium-tab" data-toggle="tab" href="#nav-premium" role="tab" aria-controls="nav-premium" aria-selected="false">Premium</a>
	  </div>
	</nav>
	<br />
	
	<%-- 정렬: 기본, 인기순 --%>
	<div class="tab-content" id="nav-tabContent">
		<c:forEach begin="1" end="3" varStatus="status">
			<c:if test="${status.index == 1 }"><c:set value="all" var="sort" /><c:set value="show active" var="active" /></c:if>
			<c:if test="${status.index == 2 }"><c:set value="classic" var="sort" /></c:if>
			<c:if test="${status.index == 3 }"><c:set value="premium" var="sort" /></c:if>
			
			<div class="tab-pane fade ${active }" id="nav-${sort }" role="tabpanel" aria-labelledby="nav-${sort }tab">
				<div class="row row-cols-1 row-cols-md-3">
					<c:forEach items="${menus }" var="menu">
						<c:if test="${sort eq menu.category || sort eq 'all'}">
				  		  <div class="col mb-4">
							<div class="card h-100">
						      <img src="${root }/img/menu/${menu.name }.jpg" class="card-img-top" alt="${menu.name }">
						      <div class="card-body">
						        <h5 class="card-title">${menu.name }</h5>
						        <p class="card-text">${menu.price }원</p>
						      </div>
						    </div>
						  </div>
						</c:if>
					</c:forEach>
				</div>
			</div>
			
			<c:remove var="active" />
		</c:forEach>
	</div>
	
	<br />
</div>
</body>
</html>