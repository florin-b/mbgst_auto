var userObj;
var globalListArticole = [];
var comandaCurenta;
var nrBorderou;
var tipFoto;
var timeStampNumar;
var timeStampMarfa;

$(document).on('pageshow', '#incarc_masini', function() {

	userObj = JSON.parse($('#userbean').text());

	$('#canvas').hide();
	tipFoto = 'NUMAR';

	$('#nrAuto').text(" ");
	$('#nrBorderou').text(" ");

});

function handleFotoButton() {
	// scrollToBottom();

	if (tipFoto == "NUMAR")
		getNrAuto();
	else if (tipFoto == "MARFA")
		saveMarfa();

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

	$('#sfarsitInc').text('');
	
	if (masina.nrMasina === undefined) {
		$('#nrAuto').text('Auto : nerecunoscut');
		$('#nrBorderou').text('');
		return;
	}

	if (masina.nrBorderou === undefined) {
		$('#nrAuto').text("Auto : " + masina.nrMasina);
		$('#nrBorderou').text('Bord : nealocat');
		return;
	}

	if (masina.statusText != '') {
		$('#nrAuto').text("Auto : " + masina.nrMasina);
		$('#nrBorderou').text('Bord : ' + masina.statusText);
		return;
	}

	timeStampNumar = masina.dataOra;

	nrBorderou = masina.nrBorderou;

	$('#nrAuto').text("Auto : " + masina.nrMasina);
	$('#nrBorderou').text("Bord : " + masina.nrBorderou);
	$('#tipFoto').text("Fotografiati marfa");
	

	tipFoto = 'MARFA';

}

function saveMarfa() {
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
			complete : function(data) {
				loading('hide');
				$('#canvas').hide();

			},
			success : function(data) {
				$.mobile.loading('hide');
				tipFoto = "NUMAR";
				showSaveImgResult(data);
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

function showSaveImgResult(data) {

	var info = $.parseJSON(JSON.stringify(data));

	if (info.succes) {
		timeStampMarfa = info.infoTime;
		$('#sfarsitInc').text("Sf. inc : " + info.infoTime);
		$('#tipFoto').text("Fotografiati nr. auto");
	} else
		$('#sfarsitInc').text('Eroare salvare date');

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
