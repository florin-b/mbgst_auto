var userObj;
var globalListArticole = [];
var comandaCurenta;
var nrBorderou;
var tipOperatie;
var timeStampNumar;
var timeStampMarfa;
var globalListMasini = [];
var nrFoto;

var canvas;
var context;
var video;
var canvas1, canvas2;
var context1, context2;
var image1, image2;

$(document).on('pageshow', '#incarc_masini', function() {

	userObj = JSON.parse($('#userbean').text());

	$('#canvas').hide();
	tipOperatie = 'NUMAR';

	$('#nrAuto').text(" ");
	$('#nrBorderou').text(" ");
	$('#divSelectMasini').hide();
	onChangeMasini();
	nrFoto = 1;

	canvas = document.getElementById('canvas');
	context = canvas.getContext('2d');
	video = document.getElementById('video');

	canvas1 = document.getElementById('canvas1');
	context1 = canvas1.getContext('2d');

	canvas2 = document.getElementById('canvas2');
	context2 = canvas2.getContext('2d');

});

function handleFotoButton() {

	if (tipOperatie == "NUMAR") {
		context.drawImage(video, 0, 0, 768, 576);
		getNrAuto();
	} else if (tipOperatie == "MARFA") {

		if (nrFoto == 1) {
			context1.drawImage(video, 0, 0, 400, 300);

			context.drawImage(video, 0, 0, 768, 576);
			image1 = document.getElementById("canvas").toDataURL("image/png")
					.replace("image/png", "image/octet-stream");

			$('#canvas1').show();
			$('#textTipFoto').text('Fotografiati marfa (2)');

		}
		if (nrFoto == 2) {
			context2.drawImage(video, 0, 0, 400, 300);

			context.drawImage(video, 0, 0, 768, 576);
			image2 = document.getElementById("canvas").toDataURL("image/png")
					.replace("image/png", "image/octet-stream");

			$('#canvas2').show();
			$('#textTipFoto').text('Salveaza datele');
			$('#fotoBtn').text('Salveaza');
			$('#fotoBtn').css('background-color', '#f5bd84');
			tipOperatie = "SALVEAZA";
		}

		nrFoto = nrFoto + 1;

	} else if (tipOperatie == "SALVEAZA") {

		saveMarfa();
	}

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

function getMasiniNeincarcate() {

	$.ajax({
		type : 'GET',
		url : 'getMasiniNeincarcate',
		data : ({
			filiala : userObj.unitLog
		}),
		beforeSend : function() {
			loading('show');
		},
		complete : function() {
			loading('hide');
		},
		success : function(data) {
			$.mobile.loading('hide');
			afisMasiniNeincarcate(data);

		},
		dataType : 'json',
		contentType : 'application/json',

		error : function(exception) {
			alert(exception);
		}

	});

}

function afisMasiniNeincarcate(listMasini) {

	globalListMasini = listMasini;

	$('#masini_select').empty();

	if (listMasini.length == 0) {
		$('#masini_select').append($('<option>', {
			value : 0,
			text : "Nu exista masini de incarcat"
		}));

	}

	if (listMasini.length > 0) {

		$('#masini_select').append($('<option>', {
			value : 0,
			text : "Selectati o masina"
		}));

		for (var i = 0; i < listMasini.length; i++) {
			$('#masini_select').append($('<option>', {
				value : listMasini[i].nrBorderou,
				text : listMasini[i].nrMasina
			}));
		}

		$("#masini_select").val("0").change();

	}

	$('#masini_select').selectmenu("refresh", true);

}

function onChangeMasini() {

	$('#masini_select').on('change', function() {
		var index = $("#masini_select option:selected").index();

		if (index > 0) {
			afisNrAutoResult(globalListMasini[index - 1]);
		}

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
				afisNrAutoResult(data);
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

function afisNrAutoResult(data) {
	var masina = $.parseJSON(JSON.stringify(data));

	$('#sfarsitInc').text('');
	//$('#fotoBtn').hide();
	$('#textTipFoto').text('');

	if (masina.nrMasina === undefined) {
		$('#textTipFoto').text("Fotografiati nr. auto");
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
		$('#fotoBtn').hide();
		return;
	}

	timeStampNumar = masina.dataOra;

	nrBorderou = masina.nrBorderou;

	$('#nrAuto').text("Auto : " + masina.nrMasina);
	$('#nrBorderou').text("Bord : " + masina.nrBorderou);

	tipOperatie = 'MARFA';
	$('#fotoBtn').show();
	$('#textTipFoto').text('Fotografiati marfa (1)');

}

function saveMarfa() {

	setSfarsitIncarcare(image1, image2);
}

function setSfarsitIncarcare(image1, image2) {

	try {
		$.ajax({
			type : 'POST',
			url : 'setSfarsitIncarcare',
			data : ({
				document : nrBorderou,
				codSofer : userObj.codPers,
				image1 : image1,
				image2 : image2
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
		$('#textTipFoto').text("Fotografiati nr. auto");
		tipOperatie = "NUMAR";
		$('#labelFoto').trigger('click');
		$('#fotoBtn').text('Foto');
		$('#fotoBtn').css('background-color', '#99FFD3');
		$('#canvas1').hide();
		$('#canvas2').hide();
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

function handleRadioClick(nrAutoRadio) {

	nrFoto = 1;

	if (nrAutoRadio.value == 'foto') {
		$('#textTipFoto').text("Fotografiati nr. auto");
		$('#divSelectMasini').hide();
		$('#fotoBtn').show();
		tipOperatie = "NUMAR";

		if ($('#sfarsitInc').text().length < 3) {
			$('#nrAuto').text(" ");
			$('#nrBorderou').text(" ");
		}

	} else if (nrAutoRadio.value == 'list') {
		$('#textTipFoto').text(" ");
		$('#nrAuto').text(" ");
		$('#nrBorderou').text(" ");
		$('#divSelectMasini').show();
		$('#fotoBtn').hide();
		$('#sfarsitInc').text(" ");
		getMasiniNeincarcate();
	}

}
