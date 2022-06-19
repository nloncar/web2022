$(document).ready(function() {
		$('#forma').submit(function(event) {
			event.preventDefault();
			let username = $('input[name="username"]').val();
		    let password = $('input[name="password"]').val();
		    let name = $('input[name="name"]').val();
		    let surname = $('input[name="surname"]').val();
		    let birthday = $('input[name="birthday"]').val();
		    let gender = $('input[name="gender"]').val();
		    
		    $('#errorNameTaken').hide();
		    $('#errorEmptyField').hide();
		    $('#success').hide();
		    
		    $.post({
		    	url: 'rest/register/newUser',
		    	data: JSON.stringify({username: username, password: password, name: name, surname: surname, birthday: birthday, gender: gender}),
		    	contentType: 'application/json',
		    	
		    	success: function(data) {
					if(data == null){
					    $('#errorNameTaken').hide().delay(3000).fadeOut();
						$('#success').show().delay(3000).fadeOut();
					}else{
						$('#errorNameTaken').show().delay(3000).fadeOut();
						$('#success').hide().delay(3000).fadeOut();
					}
				}
		    });
		});
});