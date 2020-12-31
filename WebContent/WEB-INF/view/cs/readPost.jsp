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
<script type="text/javascript" src="${root }/script/readPost.js"></script>
<link href="${root }/css/readPost.css" rel="stylesheet" type="text/css">
<script>
var ctx = '${root}';

$(function(){
	$('#remove_btn').click(function(){
		var con = confirm("Are you sure?");
		if(con){
			location.href = ctx + "/cs/remove.do?id=" + ${post.id };
		}
	});
	
	$('.removeRepl_btn').click(function(){
		var con = confirm("Are you sure?");
		var id = $(this).closest("td").prev().find(".commentId").val();
		var postId = ${post.id};
		var pageNo = ${pageNo };
		if(con){
			location.href = ctx + "/cs/reply/delete.do?id=" + id + "&postId=" + postId + "&pageNo=" + pageNo;
		}
	});
	
})
</script>
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
			    <small class="form-text text-muted d-inline">${post.customerId }</small>
			    <small class="form-text text-muted float-right d-inline">${date }</small>
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
			<a href="${root }/cs/list.do?pageNo=${pageNo }" class="btn btn-secondary" id="list">글 목록</a>
			<hr />
		</div>
	</div>
</div>


<%-- comment --%>
<div class="container">
  <div class="row">
	<div class="col-1"></div>
	<div class="col-10">
		<form action="${root }/cs/reply/add.do" method="post">
		  <input type="text" name="id" value="${post.id }" hidden=true>
		  <input type="text" name="pageNo" value="${pageNo }" hidden=true>
		  <div class="form-group d-flex justify-content-around">
		    <label for="comments">comments </label>
		    <small class="text-muted">&nbsp;${replyCnt }</small>
		    <input type="text" class="form-control mx-3" id="comments" name="comments" required>
		    <button type="submit" class="btn btn-primary float-right">Save</button>
		  </div>
		</form>
		<%-- existing comments --%>
		<table class="table">
		  <tbody>
		  	<c:if test="${empty comments }">
		  		<tr>
			  		<td colspan="2"><small class="text-muted">댓글이 없습니다. 댓글을 남겨주세요 :)</small></td>
		  		</tr>
		  	</c:if>
		  	<c:forEach items="${comments }" var="comment">
				<tr>
					<td class="px-0">
						<c:if test="${comment.stateChangable }">
								<c:set var="manager" value="manager" />
						</c:if>
							
				  		<form action="${root }/cs/reply/modify.do" method="post" class="d-flex justify-content-around align-items-center px-0">
							<input type="text" name="postId" value="${post.id }" hidden=true>
							<input type="text" name="pageNo" value="${pageNo }" hidden=true>
							<input type="text" class="commentId" name="commentId" value="${comment.id }" hidden=true>
							<input type="text" class="form-control border-0 bg-white" name="customerId" value="${comment.customerId }" readonly>
						    <input type="text" class="form-control border-0 bg-white ${manager } comments" name="comments" value="${comment.comment }" required readonly>
						    <fmt:formatDate var="Cdate" value="${comment.date }" pattern="yyyy-MM-dd HH:mm"/>
						    <input type="text" class="form-control border-0 bg-white" name="date" value="${Cdate }" readonly>
						</form>
						
						<c:remove var="manager" />
					</td>
					<td class="px-0">
					   <div class="d-flex justify-content-around align-items-center">
						  <c:if test="${comment.customerId eq sessionScope.user.id }">
						    <button type="submit" class="btn btn-white float-right submitRepl_btn p-1">Save</button>
						    <button class="btn btn-white float-right cancelRepl_btn p-1">Cancel</button>
						    <button class="btn btn-white float-right modifyRepl_btn p-1">Modify</button>
						    <button class="btn btn-white float-right removeRepl_btn p-1">Remove</button>
						  </c:if>	
					    </div>
					</td>
				</tr>
		  	</c:forEach>
		  </tbody>
		</table>
	</div>
  </div>
</div>
</body>
</html>