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
	
	
	var comments;
	$('.submitRepl_btn').hide();
	$('.cancelRepl_btn').hide();
	
	$('.modifyRepl_btn').click(function(){
		$('.submitRepl_btn').hide();
		$('.cancelRepl_btn').hide();
		$('.modifyRepl_btn').show();
		$('.removeRepl_btn').show();
		
		$(this).hide();
		$(this).next().hide();
		$(this).prev().prev().show();
		$(this).prev().show();
		
		$(this).closest("td").prev().find('.comments').removeAttr("readonly");
		comments = $(this).closest("td").prev().find('.comments').val();
	});
	
	$('.cancelRepl_btn').click(function(){// 취소하면 댓글 원래대로!!!!
		$(this).next().show();
		$(this).siblings('.removeRepl_btn').show();
		$(this).prev().hide();
		$(this).hide();
			
		$(this).closest("td").prev().find('.comments').val(comments);
		$(this).closest("td").prev().find('.comments').attr("readonly", true);
	});
	
	$('.submitRepl_btn').click(function(){
		$(this).closest("td").prev().find('form').submit();
	});

})