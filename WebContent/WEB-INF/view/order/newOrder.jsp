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
<script type="text/javascript" src="${root }/script/newOrder.js"></script>
<link href="${root }/css/newOrder.css" rel="stylesheet" type="text/css">
<script>
$(function(){/* 나중에 ajax로 다 처리하자..!!! */
	var url = "${root }/cart";/* or "cart" */ 
	
	$('footer').html("");
	$('footer').append("<ul id='cartLi' class='overflow-auto rounded'></ul>")
	JSON.parse('${cartList }').forEach(function(item){
		$('#cartLi').append("<li class='list-group-item d-flex justify-content-between align-items-center'>" /*height줘서 스크롤 보이게, center*/
				+ item.menuName
				+ "<span class='badge badge-primary badge-pill'>"
				+ item.ea
				+ "</span>"
				+ "</li>");
	});
	
	$('.addToCart').click(function(){
		var data = {
			menuId: $(this).prev().find('.menuId').val(),
			ea: $(this).prev().find('div').find('input').val()
		};
		$.post(url, data, function(data){
			$('footer').html("");
			$('footer').append("<ul id='cartLi' class='overflow-auto rounded'></ul>")
			data.forEach(function(item) {
				$('#cartLi').append("<li class='list-group-item d-flex justify-content-between align-items-center'>" /*height줘서 스크롤 보이게, center*/
						+ item.menuName
						+ "<span class='badge badge-primary badge-pill'>"
						+ item.ea
						+ "</span>"
						+ "</li>");
				
			});
		});
	});
	
	var myPoint = ${sessionScope.user.point };
	console.log(myPoint);
	if(point == 0){
		$('#point').attr("disabled", true);
		$('#usePoints').attr("disabled", true);
	}
	$('#usePoints').click(function(){
		$('#input').val(myPoint);
	});
	
	$('#point').focusout(function(){
		var usePoint = $('#point').val();
		
		if(usePoint > myPoint){
			alert('사용 가능 최대 포인트는 ' + myPoint + '원입니다.');
			$('#input').val(myPoint);// 왜 안 되지
		}
	})
});
</script>
<title>EJ's Pizza Store</title>
</head>
<body>
<t:navbar />

<div class="container mt-5"  data-spy="scroll" data-target="#prev" data-offset="0">
	<h2 class="text-center">Menu</h2>
	<small class="form-text text-muted text-center">Choose one or more menu.</small>
	<br />
	
	<nav>
	  <div class="nav nav-tabs" id="nav-tab" role="tablist">
	    <a class="nav-link active" id="nav-all-tab" data-toggle="tab" href="#nav-all" role="tab" aria-controls="nav-all" aria-selected="true">All</a>
	    <a class="nav-link" id="nav-classic-tab" data-toggle="tab" href="#nav-classic" role="tab" aria-controls="nav-classic" aria-selected="false">Classic</a>
	    <a class="nav-link" id="nav-premium-tab" data-toggle="tab" href="#nav-premium" role="tab" aria-controls="nav-premium" aria-selected="false">Premium</a>
	  </div>
	</nav>
	<br />
	
	<%-- 저장된 피자 메뉴 읽어오기 --%>
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
						        <div class="text-center"> <!-- form에서 div로 변경함 -->
						        	<input type="text" name="menuId" class="menuId" value="${menu.id }" hidden=true />
							        <div class="rounded-pill border py-2">
										<i class="fas fa-minus"></i>
										<input type="number" class="border-0 text-center" name="ea" value="1" readonly>
										<i class="fas fa-plus"></i>
									</div>
						        </div>
							    <button class="btn btn-primary float-right mt-2 addToCart">Add to Cart</button>
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
	
	<div class="text-center">
		<a class="btn btn-primary" id="next" href="#">Next</a>
	</div>
	<br />
	
	<footer></footer>
</div>







<div class="container mt-5" data-spy="scroll" data-target="#next" data-offset="0">
	<div class="row">
		<div class="col-3"></div>
		<div class="col-6">
			<h2 class="text-center">Check your information.</h2>
			<br />

			<form action="${root }/order.do" method="post">
			
				<h5>주문자 정보</h5>
				<div class="form-group">
				    <label for="name">Name</label>
				    <input type="text" class="form-control" id="name" name="name" value="${sessionScope.user.name }" required disabled>
			    </div>
			    <div class="form-group">
				    <label for="phone">Phone</label>
				    <input type="text" class="form-control" id="phone" name="phone" value="${sessionScope.user.phone }" required disabled>
			    </div>
		
				<c:if test="${dorw eq 0 }"> <!-- if delivery -->
				  <div class="form-group">
				    <label for="address">Address</label>
				    <input type="text" class="form-control" id="address" name="address" value="${sessionScope.user.address }" required>
				  </div>
				</c:if>
				
			
				<input type="text" name="customerId" value="${sessionScope.user.id }" hidden=true />
				<div class="form-group">
				    <label for="store">Store</label>
				    <select class="form-control" name="store" id="store" required>
				    	<c:forEach items="${stores }" var="store">
				    		<option value="${store.id }">${store.name }</option> <%-- 위치 보기 modal 지도... --%>
				    	</c:forEach>
			  		</select>
			    </div>
				
				<br />
			
				<h5>결제 정보</h5>
				<small class="text-muted">Usable Point: ${sessionScope.user.point }</small> <br />
			    <label for="usePoints">use all points</label> <input type="checkbox" id="usePoints"> 
				<div class="form-group">
			    	<input type="number" class="form-control" id="point" name="point" value="0" required>
				</div>
				
				<div class="form-group">
				    <label for="payment">Payment</label>
					  <input class="btn btn-light form-control payment" value="Card" readonly>
					  <input class="btn btn-light form-control payment" value="Phone" readonly>
					  <input class="btn btn-light form-control payment" value="Cash" readonly>
					  <small class="text-muted">* Cash is face-to-face payment.</small>
			    </div>
			    
			    <div class="text-center">
			    	<a class="btn btn-light" id="prev" href="#">Prev</a>
			    	<button type="submit" class="btn btn-primary">Order</button>
			    </div>
			</form>
			<br />
		</div>
	</div>
</div>
</body>
</html>