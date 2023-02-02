function load(user)
{
		document.getElementById("username").innerHTML = user.username;
		document.getElementById("name").innerHTML = user.name;
		document.getElementById("surname").innerHTML = user.surname;
		document.getElementById("address").innerHTML = user.address;
		document.getElementById("birthday").innerHTML = user.birthday;
		document.getElementById("gender").innerHTML = user.gender;
}

$(document).ready(function() {	
		    $.get({
				url: '../web2022/rest/users/currentUser',
				success : function(currentUser){
					load(currentUser);
					},
				});
			
});