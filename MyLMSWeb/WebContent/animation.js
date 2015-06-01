$(document).ready(function(){
	
	
	//$('li.fields').filter(':nth-child(n+4)').addClass('hide');
	
	$('li.title').next().slideToggle(0.1).siblings('li.fields').slideToggle(0.1);	
	
	$('li.fields').slideUp(0.1);
	$('li.title').click(function(){
		
		
		
		$(this).next().slideToggle(200);
		$('#table').attr('style','display:table');
		
			
	});
});

	
	
	
	
