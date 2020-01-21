var userObj;
var globalListArticole = [];
var comandaCurenta;
var nrBorderou;

$(document).on('pageshow', '#incarc_masini', function() {

	userObj = JSON.parse($('#userbean').text());

	$('#canvas').hide();

});

function showSaveButton() {
	scrollToBottom();
	getNrAuto();

}

function scrollToCamDiv() {

	$(function() {
		$('html, body').animate({
			scrollTop : $('#camdiv').offset().top
		});
	});
}

function scrollToBottom() {

	$(function() {
		$('html, body').animate({
			scrollTop : $('#pageBottom').offset().top
		});
	});
}

function onClickRefreshList() {

	$("#refreshList").click(function() {
		getMasiniNeincarcate();
	});

}

function getNrAuto() {

	var image = document.getElementById("canvas").toDataURL("image/png")
			.replace("image/png", "image/octet-stream");

	try {
		$.ajax({
			type : 'POST',
			url : 'valideazaMasina',
			async : true,
			data : ({
				codUser : userObj.codPers,
				filiala : userObj.unitLog,
				image : image
			}),
			beforeSend : function() {
				loading('show');
			},
			complete : function() {
				loading('hide');
			},
			success : function(data) {
				$.mobile.loading('hide');
				checkResult(data);
			},
			error : function(data) {
				$.mobile.loading('hide');
				alert('Error: ' + $.parseJSON(data));

			}

		});
	} catch (error) {
		alert(error);
	}

}

function checkResult(data) {
	var masina = $.parseJSON(JSON.stringify(data));

	if (masina.nrMasina === undefined) {
		$("#nrAuto").css("color", "red");
		$('#nrAuto').text('Acest numar auto nu poate fi recunoscut.');

		scrollToBottom();
		return;
	}

	if (masina.nrBorderou === undefined) {
		$("#nrAuto").css("color", "red");
		$('#nrAuto').text(
				'Pentru masina ' + masina.nrMasina
						+ ' nu exista un borderou alocat.');
		scrollToBottom();
		return;
	}

	nrBorderou = masina.nrBorderou;

	var textSucces = 'Masina ' + masina.nrMasina + ' a fost incarcata la '
			+ masina.dataOra;

	$("#nrAuto").css("color", "green");
	$('#nrAuto').text(textSucces);
	scrollToBottom();

	var image = document.getElementById("canvas").toDataURL("image/png")
			.replace("image/png", "image/octet-stream");

	setSfarsitIncarcare(image);

}

function setSfarsitIncarcare(image) {

	try {
		$.ajax({
			type : 'POST',
			url : 'setSfarsitIncarcare',
			data : ({
				document : nrBorderou,
				codSofer : userObj.codPers,
				image : image
			}),
			beforeSend : function() {
				loading('show');

			},
			complete : function() {
				loading('hide');

				$('#canvas').hide();

			},
			success : function(data) {
				$.mobile.loading('hide');
			},
			error : function(data) {
				$.mobile.loading('hide');
				alert('Error: ' + $.parseJSON(data));

			}

		});
	} catch (error) {
		alert(error);
	}

}

function loading(showOrHide) {
	setTimeout(function() {
		$.mobile.loading(showOrHide);
	}, 1);
}

function showAlertDialog(tipAlert, mesajAlert) {

	$('#tipAlertM').text(tipAlert);
	$('#textAlertM').text(mesajAlert);
	$.mobile.changePage('#dialogIncarcare', {
		transition : "none"
	});
}
