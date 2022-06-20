$(document).ready(function() {
	$('#forma').submit(function(event) {
		event.preventDefault();
		
		let username = $('input[name="username"]').val();
		let password = $('input[name="password"]').val();
		
		$('#error').hide();
		
		$.post({
			url: 'rest/user/login',			
			data: JSON.stringify({username: username, password: password}),
			contentType: 'application/json',
			
			success: function(data) {
				if(data != null){
					$("#success").show().delay(3000).fadeOut(function(){
						window.location.href="/objekti.html";
					})
				}
				else
				{
					$('#error').show().delay(3000).fadeOut();
				}
			}
		});
	});
});
