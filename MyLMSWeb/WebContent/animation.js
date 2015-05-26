$(document).ready(function(){
	
	
	//$('li.fields').filter(':nth-child(n+4)').addClass('hide');
	
	$('li.title').next().slideToggle('fast').siblings('li.fields').slideToggle('fast');	
	
	$('li.fields').slideUp(200);
	$('li.title').click(function(){
		
		
		
		$(this).next().slideToggle(200);
		
		
			
	});
});

	
	
	
	
