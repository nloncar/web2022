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
	//let tdSad = $('<td>' + product.sadrzaj + '</td>');
	let tdStatus = $('<td>' + product.lokacija + '</td>');
	let tdRV = $('<td>' + product.radno_vreme + '</td>');
	tr.append(tdNaziv).append(tdOcena).append(tdTip).append(tdStatus).append(tdRV);
	tr.click(clickClosure(product));
	$('#tabelaB').append(tr);
	//$('#tabela tbody').append(tr);
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
	
	$('button#pretraga').click(function() {
		$('form#pretrazi').show();
		$('form#dodavanje').hide();
		
	});
	
		$('button#dodaj').click(function() {
		$('form#pretrazi').hide();
		$('form#dodavanje').show();
	});
	
	$('form#pretrazi').submit(function(event) {
		event.preventDefault();
		
		let naziv = $('input[name="name"]').val();
		let price = $('input[name="price"]').val();
		let type = $('input[name="kategorija"]').val();
		if (!price || isNaN(price)) {
			$('#error').text('Morate uneti naziv!');
			$("#error").show().delay(3000).fadeOut();
			return;
		}
		$('p#error').hide();
		$.post({
			url: 'rest/products/find',
			data: JSON.stringify({id: '', name: naziv, price: price, type: type}),
			contentType: 'application/json',
			success: function(product) {
				$('#success').text('Uspešna pretraga!');
				$("#success").show().delay(3000).fadeOut();
				// Dodaj novi proizvod u tabelu
				addProductTr(product);
			}
		});
	});
	
	$('form#dodavanje').submit(function(event) {
		
		
		event.preventDefault();
		console.log("Forma dodavanje");
		let name = $('input[name="name1"]').val();
		let price = $('input[name="price1"]').val();
		let type = $('input[name="kategorija1"]').val();
		let lokacija = $('input[name="lokacija1"]').val();
		let rv = $('input[name="rv1"]').val();
		let status = $('input[name="status1"]').val();
		if (!name || Nan(price) || !type || !lokacija || !rv ) {
			$('#error1').text('Morate uneti sve podatke!');
			$("#error1").show().delay(3000).fadeOut();
			return;
		}
		$('p#error1').hide();
		$.post({
			url: '../web2022/rest/products/addNew',
			data: JSON.stringify({id: '', name: name, ocena: price, tip_objekta: type, lokacija: lokacija, radno_vreme: rv}),
			contentType: 'application/json',
			success: function(product) {
				$('#success1').text('Uspešna pretraga!');
				$("#success1").show().delay(3000).fadeOut();
				// Dodaj novi proizvod u tabelu
				addProductTr(product);
			}
		});
	});
});