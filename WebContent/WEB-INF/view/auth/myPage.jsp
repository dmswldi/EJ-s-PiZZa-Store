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
	<h2 class="text-center">마이페이지</h2>
	<br />
	<div class="card">
	  <img src="${root }/img/myIcon.png" class="card-img-top mx-auto mt-5" alt="myIcon" style="width:100px; height:100px;">

	  <div class="card-body text-center">
	    <h5 class="card-title">${sessionScope.user.name }</h5>
	    <p class="card-text"><small class="text-muted">${sessionScope.user.name }님, 안녕하세요.</small></p>
	  </div>
	  
	  <div class="row mb-3">
	  	<div class="col-1"></div>
	  	<div class="col-10">
	  	<div class="card-deck">
		  <div class="card">
		    <div class="card-body text-center">
			  <h5 class="card-title">적립금</h5>
			  <p class="card-text">${sessionScope.user.point }원</p>
		    </div>
		  </div>
		  <div class="card">
		    <div class="card-body text-center">
			  <button class="btn btn-white"><h5>주문내역</h5></button>
		    </div>
		  </div>
	  	</div>
	  </div>
	  	</div>
	  
      <div class="list-group list-group-flush list-hover">
        <button class="list-group-item list-group-item-action" onclick="location.href='${root}/cs/mylist.do'">내 글 보기</button>
        <button class="list-group-item list-group-item-action" onclick="location.href='${root}/modify.do'">회원 정보 수정</button>
        <button class="list-group-item list-group-item-action" onclick="location.href='${root}/cs/list.do'">고객센터</button>
	    <div class="list-group-item list-group-item-action"><a href="${root }/withdrawal.do" class="card-link"><small>회원 탈퇴</small></a></div>
      </div>
	 
	</div>
</div>
</body>
</html>