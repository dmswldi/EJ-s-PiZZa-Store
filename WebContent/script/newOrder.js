$(function(){
	$('.container').last().hide();
	
	$('#next').click(function(){
		$('.container').last().prev().hide();
		$('.container').last().show();
	});
	
	$('#prev').click(function(){
		$('.container').last().prev().show();
		$('.container').last().hide();
	});
	
	$('.payment').click(function(){
		$('.payment').css('background-color', '#f8f9fa');
		$(this).css('background-color', '#ced4da');
	});
	
	

	stickyFooter();
	$(window).scroll(stickyFooter).resize(stickyFooter);

	function stickyFooter(){
		document_height = $(document).height();
		document_scrollTop = $(document).scrollTop();
		window_height = $(window).height();
		footer_height = $("footer").height();

		gap = document_height - footer_height - window_height;/* 1460 */
		bottom = document_scrollTop - gap;
		//console.log(document_height - window_height - document_scrollTop);
		/*console.log(gap);1460 1460*/
		/*console.log(bottom);24 -1460*/

		if(document_scrollTop > gap){
			$("footer").css("bottom", bottom+"px");
		}else{
			$("footer").css("bottom","0");
		}
	}
	
	
	$('.fa-minus').css('cursor', 'pointer');
	$('.fa-plus').css('cursor', 'pointer');
	
	$('.fa-minus').click(function(){
		var value = $(this).next().val();
		if(value > 1){
			value = Number(value) - 1;
			$(this).next().val(value);
		}
	});
	
	$('.fa-plus').click(function(){
		var value = $(this).prev().val();
		value = Number(value) + 1;
		$(this).prev().val(value);
	});
});