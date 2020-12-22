<%@page import="init.ConnectionProvider"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<link href="${root }/css/home.css" rel="stylesheet" type="text/css">
<title>EJ's Pizza Store</title>
</head>
<body>
<t:navbar />
<div class="container-fluid px-0">
	<div class="carousel-wrapper d-flex align-items-center"> <%-- 이미지 꽉 차게 --%>
		<div id="carouselExampleInterval" class="carousel slide" data-ride="carousel"  data-interval="10000">
		  <div class="carousel-inner">
		    <div class="carousel-item active">
		      <img src="${root }/img/home3.jpg" class="d-block w-100" alt="home0">
		    </div>
		    
		    <div class="carousel-item">
		      <img src="${root }/img/home4.jpg" class="d-block w-100" alt="home1">
		    </div>
		    <div class="carousel-item">
		      <img src="${root }/img/home5.jpg" class="d-block w-100" alt="home2">
		    </div>
		    
		  </div>
		  <a class="carousel-control-prev" href="#carouselExampleInterval" role="button" data-slide="prev">
		    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    <span class="sr-only">Previous</span>
		  </a>
		  <a class="carousel-control-next" href="#carouselExampleInterval" role="button" data-slide="next">
		    <span class="carousel-control-next-icon" aria-hidden="true"></span>
		    <span class="sr-only">Next</span>
		  </a>
		</div>
	</div>
</div>

<div class="container my-4">
  <div class="row">
    <div class="col-5">
      <button class="btn jumbotron jumbotron-fluid rounded">
		  <div class="container-fluid text-center">
		    <h1 class="display-4">매주 월요일은 <span>50%</span> 피자 할인</h1>
		  </div>
	  </button>
    </div>
    <div class="col-2">
    </div>
    <div class="col-5 rounded">
	  <button class="btn jumbotron jumbotron-fluid rounded">
		  <div class="container-fluid text-center">
		    <h1 class="display-4">신메뉴를 추천해주세요!</h1>
		  </div>
	  </button>
	</div>
  </div>
</div>
</body>
</html>