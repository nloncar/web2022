// Closure koji će zapamtiti trenutni proizvod
function clickClosure(product){
	return function() {
		// Parametar product prosleđen u gornju funkciju će biti vidljiv u ovoj
		// Ovo znači da je funkcija "zapamtila" za koji je proizvod vezana
		$('tr.selected').removeClass('selected');
		$(this).addClass('selected');
	};
}

function addProductTr(product) {
	let tr = $('<tr></tr>');
	let tdNaziv = $('<td>' + product.name + '</td>');
	let tdOcena = $('<td>' + product.ocena + '</td>');
	let tdTip = $('<td>' + product.tip_objekta + '</td>');
	let tdSad = $('<td>' + product.sadrzaj + '</td>');
	let tdStatus = $('<td>' + product.status + '</td>');
	let tdRV = $('<td>' + product.radno_vreme + '</td>');
	tr.append(tdNaziv).append(tdOcena).append(tdTip).append(tdSad).append(tdStatus).append(tdRV);
	tr.click(clickClosure(product));
	$('#tabela tbody').append(tr);
}

$(document).ready(function() {
	$.get({
		url: 'rest/products',
		success: function(products) {
			for (let product of products) {
				addProductTr(product);
			}
		}
	});
	
	$('button#dodaj').click(function() {
		$('form#pretrazi').show();
	});
	
	$('form#pretrazi').submit(function(event) {
		event.preventDefault();
		let name = $('input[name="name"]').val();
		let price = $('input[name="price"]').val();
		let type = $('input[name="kategorija"]').val();
		if (!name || isNaN(name)) {
			$('#error').text('Morate uneti naziv!');
			$("#error").show().delay(3000).fadeOut();
			return;
		}
		$('p#error').hide();
		$.post({
			url: 'rest/products/find',
			data: JSON.stringify({id: '', name: name, price: price, type: type}),
			contentType: 'application/json',
			success: function(product) {
				$('#success').text('Novi proizvod uspešno kreiran.');
				$("#success").show().delay(3000).fadeOut();
				// Dodaj novi proizvod u tabelu
				addProductTr(product);
			}
		});
	});
});