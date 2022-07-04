$(document).ready(function() {
		$('#forma').submit(function(event) {
			event.preventDefault();
			let username = $('input[name="username"]').val();
		    let password = $('input[name="password"]').val();
		    let name = $('input[name="name"]').val();
		    let surname = $('input[name="surname"]').val();
		    let birthday = $('input[name="birthday"]').val();
		    let gender = $('input[name="gender"]').val();
		    
		    $.post({
		    	url: 'rest/register',
		    	data: JSON.stringify({username: username, password: password, name: name, surname: surname, birthday: birthday, gender: gender}),
		    	contentType: 'application/json',
		    	
success: function(data) {
				if(data==null){
					$('#error').text('Korisnicko ime je vec zauzeto.');
					$("#error").show().delay(3000).fadeOut();
				}
				else {
					$('#success').text('Registracija uspesna.');
					$("#success").show().delay(3000).fadeOut(function(){
						window.location.href="/login.html";
					});
				}
			}
		    });
		});
});