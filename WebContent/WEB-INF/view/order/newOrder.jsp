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
	<h2 class="text-center">Menu</h2>
	<small class="form-text text-muted text-center">Choose one or more menu.</small>
	<br />
	
	<%-- 저장된 피자 메뉴 읽어와서 forEach 돌려야 돼 --%>
	<ul class="nav nav-tabs" style="font-family: 'Noto Sans KR'; font-size: 8px;">
	  <li class="nav-item">
	    <a class="nav-link active" href="#">All</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" href="#">Standard</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" href="#">Premium</a>
	  </li>
	</ul>
	
	
	<br />
	
	
	<div class="row row-cols-1 row-cols-md-3">
	  <div class="col mb-4">
	    <div class="card h-100">
	      <img src="${root }/img/brown-pepper-pizza.jpg" class="card-img-top" alt="brown-pepper-pizza">
	      <div class="card-body">
	        <h5 class="card-title">Brown-Pepper-Pizza</h5>
	        <p class="card-text">15,900원</p>
	      </div>
	    </div>
	  </div>
	  <div class="col mb-4">
	    <div class="card h-100">
	      <img src="..." class="card-img-top" alt="...">
	      <div class="card-body">
	        <h5 class="card-title"></h5>
	        <p class="card-text"></p>
	      </div>
	    </div>
	  </div>
	  <div class="col mb-4">
	    <div class="card h-100">
	      <img src="..." class="card-img-top" alt="...">
	      <div class="card-body">
	        <h5 class="card-title"></h5>
	        <p class="card-text"></p>
	      </div>
	    </div>
	  </div>
	  <div class="col mb-4">
	    <div class="card h-100">
	      <img src="..." class="card-img-top" alt="...">
	      <div class="card-body">
	        <h5 class="card-title"></h5>
	        <p class="card-text"></p>
	      </div>
	    </div>
	  </div>
	</div>
	<div class="text-center">
		<button class="btn btn-primary">Next</button>
	</div>
</div>







<div class="container mt-5"> <%-- hidden 해뒀다가 메뉴 고르고 다음 누르면 얘만 보이게 --%>
	<div class="row">
		<div class="col-3"></div>
		<div class="col-6">
			<h2 class="text-center">Check your information.</h2>
			<br />
			<form>
				<h5>주문자 정보</h5>
				<div class="form-group">
				    <label for="name">Name</label>
				    <input type="text" class="form-control" id="name" name="name" value="${sessionScope.user.name }" required>
			    </div>
			    <div class="form-group">
				    <label for="phone">Phone</label>
				    <input type="text" class="form-control" id="phone" name="phone" value="${sessionScope.user.phone }" required>
			    </div>
		
				<c:if test="${hotg eq 'delivery' }">
				  <div class="form-group">
				    <label for="address">Address</label>
				    <input type="text" class="form-control" id="address" name="address" value="${sessionScope.user.address }" required>
				  </div>
				</c:if>
			</form>
			
			<form action="${root }/order.do" method="post">
			
				<input type="text" name="customerId" value="${sessionScope.user.id }" hidden=true />
				<div class="form-group">
				    <label for="store">Store</label>
				    <select class="form-control" name="store" id="store" required>
				  		<option value="">store1</option> <%-- 위치 보기 modal 지도... --%>
				  		<option value="">store2</option>
				  		<option value="">store3</option>
			  		</select>
			    </div>
			
<!-- 				메뉴 고르고, 결제 수단, 포인트 사용 -->
				<h5>결제 정보</h5>
				<div class="form-group form-check">
					<p>Usable Point: ${sessionScope.user.point }</p>
				    <label class="form-check-label">
			      		<input class="form-check-input" type="checkbox"> use point
			    	</label>
			    	<input type="number" class="form-control" id="point" name="point" value="${sessionScope.user.point }" required>
				</div>
				
				<div class="form-group">
				    <label for="payment">Payment</label>
				   	<a class="btn btn-light">Cash</a>
				   	<a class="btn btn-light mx-2">Card</a>
				   	<a class="btn btn-light">Phone</a>
				   	<small class="text-muted">Cash is face-to-face payment.</small>
			    </div>
			    
			    <div class="text-center"> <%-- 왜 둘 다 해야 되지? --%>
			    	<button type="submit" class="btn btn-primary">Order</button>
			    </div>
			</form>
			
		</div>
	</div>
</div>
</body>
</html>