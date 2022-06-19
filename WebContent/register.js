$(document).ready(function() {
		$('#registerForm').submit(function(event) {
			event.preventDefault();
			let username = $('input[name="username"]').val();
		    let password = $('input[name="password"]').val();
		    let name = $('input[name="name"]').val();
		    let surname = $('input[name="surname"]').val();
		    let birthday = $('input[name="birthday"]').val();
		    let gender = $('input[name="gender"]').val();
		    
		    $('#errorNameTaken').hide();
		    $('#errorEmptyField').hide();
		    
		    $.post({
		    	url: 'rest/register',
		    	data: JSON.stringify({username: username, password: password, name: name, surname: surname, birthday: birthday, gender: gender}),
		    	contentType: 'application/json',
		    	success: function() {
					
				},
				error: function(){
					
				}
		    });
		});
});