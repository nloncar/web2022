// Closure koji će zapamtiti trenutni proizvod
function clickClosure(product){
	return function() {
		// Parametar product prosleđen u gornju funkciju će biti vidljiv u ovoj
		// Ovo znači da je funkcija "zapamtila" za koji je proizvod vezana
		$('tr.selected').removeClass('selected');
		$(this).addClass('selected');
	};
}

function refreshPage(){
    window.location.reload();
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
		if (!naziv && isNaN(price) && !type ) {
			$('#error').text('Morate uneti neki parametar za pretragu!');
			$("#error").show().delay(3000).fadeOut();
			return;
		}
		$('p#error').hide();
		

		
		$.ajax({
			url: 'rest/products/find/' + naziv,
			type:'PUT',
			contentType: 'application/json',
			success: function(products) {
				$('#success').text('Uspešna pretraga!');
				$("#success").show().delay(3000).fadeOut();
				// Dodaj novi proizvod u tabelu
				//addProductTr(product);
				console.log("Forma pretraga uspešna");
				$('#tabelaB').empty();
				for (let product of products) {
					console.log(product.name);
				addProductTr(product);
			}
			}
		});
	});
	
	$('form#dodavanje').submit(function(event) {
		
		
		event.preventDefault();
		
		
		console.log("Forma dodavanje");
		let name1 = $('input[name="name1"]').val();
		let price1 = $('input[name="price1"]').val();
		let type1 =$('select[name=kategorija]').val();
		let lokacija1 = $('input[name="lokacija1"]').val();
		let rv1 = $('input[name="rv1"]').val();
		let status1 = $('input[name="status1"]').val();

		if (!name1  || !lokacija1 || !rv1 ) {
			$('#error1').text('Morate uneti sve podatke!');
			$("#error1").show().delay(3000).fadeOut();
			return;
		}
		$('p#error1').hide();
		$.post({
			url: '../web2022/rest/products/addNew',
			data: JSON.stringify({id: '', name: name1, ocena: price1, tip_objekta: type1, lokacija: lokacija1, radno_vreme: rv1}),
			contentType: 'application/json',
			success: function(product) {
				$('#success1').text('Uspešna pretraga!');
				$("#success1").show().delay(3000).fadeOut();
				console.log("Forma dodavanje");
				// Dodaj novi proizvod u tabelu
				addProductTr(product);
			}
		});
	});
});