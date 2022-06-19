$(document).ready(function() {
	$('#forma').submit(function(event) {
		event.preventDefault();
		
		let username = $('input[name="username"]').val();
		let password = $('input[name="password"]').val();
		
		$('#error').hide();
		
		$.post({
			url: 'rest/login/user',
			
			data: JSON.stringify({username: username, password: password}),
			contentType: 'application/json',
			
			success: function(data) {
				if(data != null){
				
				}
				else
				{
					$('#error').show().delay(3000).fadeOut();
				}
			}
		});
	});
});
