function load(user)
{
		document.getElementById("password").value = user.password;
		document.getElementById("name").value = user.name;
		document.getElementById("surname").value = user.surname;
		document.getElementById("birthday").value = user.birthday;
		document.getElementById("gender").value = user.gender;
		if(user.gender === "Muski")
		{
			document.getElementById("genderMale").click();
		}
		if(user.gender === "Zenski")
		{
			document.getElementById("genderFemale").click();
		}
}

$(document).ready(function() {
	
		$.get({
		url: '../web2022/rest/users/currentUser',
		success : function(currentUser){
				load(currentUser);
			}
		});
		
		$('#forma').submit(function(event) {
			event.preventDefault();
			
		    let password = $('input[name="password"]').val();
		    let name = $('input[name="name"]').val();
		    let surname = $('input[name="surname"]').val();
		    let birthday = $('input[name="birthday"]').val();
		    let gender = $('input[name="gender"]').val();
		    

		    $.post({
		    	url: '../web2022/rest/users/edit',
		    	data: JSON.stringify({username: null, password: password, name: name, surname: surname, birthday: birthday, gender: gender}),
		    	contentType: 'application/json',
		    	
success: function() {
				window.location.href="userProfile.html";
			}
		    });
		});
});