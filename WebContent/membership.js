function loadPackages(membership) {
	let tr = $('<tr></tr>');
	let tdType = $('<td>' + membership.type + '</td>');
	let tdDuration = $('<td>' + membership.duration + '</td>');
	let tdPrice = $('<td>' + membership.price + '</td>');
	if(product.maxEntries == '0' )
	{
			let tdMaxEntries = $('<td>' + "neograniceni" + '</td>');
	}
	else
	{
	let tdMaxEntries = $('<td>' + product.maxEntries + '</td>');
	let btn = document.createElement("button");
		btn.innerHTML = "uplati";
		btn.type = "submit";
		btn.name = membership.type;
		btn.addEventListener("click", addMembership(membership.type));
	}
	tr.append(tdType).append(tdDuration).append(tdDuration).append(tdPrice).append(tdMaxEntries).append(tdRV);
	tr.click(clickClosure(product));
	$('#tabela tbody').append(tr);
}

function addMembership(membershipType)
{
	$.post({
		url: 'rest/users/addMemberhip',
		data: JSON.stringify({memebershipType : membershipType}),
		contentType: 'application/json',
		success: function() {
					$('#success').text('Uspesna prijava clanarine.');
					showCurrentMembership();
					$("#success").show().delay(3000).fadeOut(function(){
					});
			}
	});
}

function showCurrentMembership(){

$.get({
	url: 'rest/users/getCurrentMembership',
	success : function(currentMembership){
		document.getElementById("membershipType").innerHTML = currentMembership.type;
		document.getElementById("billingDate").innerHTML = currentMembership.billingDate;
		document.getElementById("expirationDate").innerHTML = currentMembership.expirationDate;
		document.getElementById("maxEntries").innerHTML = currentMembership.maxEntries;
		document.getElementById("usedEntries").innerHTML = currentMembership.usedEntries;
	}

});
}


$(document).ready(function() {
	$.get({
		url: 'rest/users/getMemberPackages',
		success: function(memberships) {
			for (let membership of memberships) {
				loadPackages(membership);
			}
		}
	});
	showCurrentMembership();
});