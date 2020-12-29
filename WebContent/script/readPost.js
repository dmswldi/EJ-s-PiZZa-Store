/**
 * 
 */
$(function(){
	$('#submit_btn').hide();
	$('#cancel_btn').hide();
	
	$('#modify_btn').click(function(){
		$('#modify_btn').hide();
		$('#remove_btn').hide();
		$('#submit_btn').show();
		$('#cancel_btn').show();
		
		$('#category').removeAttr("disabled");
		$('#title').removeAttr("readonly");
		$('#content').removeAttr("readonly");
	});
	
	$('#cancel_btn').click(function(){
		$('#modify_btn').show();
		$('#remove_btn').show();
		$('#submit_btn').hide();
		$('#cancel_btn').hide();
		
		$('#category').attr("disabled", true);
		$('#title').attr("readonly", true);
		$('#content').attr("readonly", true);
	});
	
	$('#remove_btn').click(function(){
		var con = confirm("Are you sure?");
		if(con){
			location.href = root + "/cs/remove.do?id=" + id;// root가 안 먹어!!!!!
		}
	});
	
	$('#list').click(function(){
		//history.go(-1);
		location.href = root + "/cs/list.do?pageNo=" + pageNo;
	});
})