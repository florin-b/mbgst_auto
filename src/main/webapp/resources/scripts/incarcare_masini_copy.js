var userObj;
var globalListArticole = [];
var comandaCurenta;
var nrBorderou;

$(document).on('pageshow', '#incarc_masini', function() {

	userObj = JSON.parse($('#userbean').text());

	$('#canvas').hide();

	// getMasiniNeincarcate();

	// onChangeMasini();

	onClickIncarcare();
	// onClickRefreshList();

});

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

			// afisMasiniNeincarcate(data);

		},
		dataType : 'json',
		contentType : 'application/json',

		error : function(exception) {
			alert(exception);
		}

	});

}

function showSaveButton() {

	$('#canvas').show();

	// $('#sfIncarcare').show();
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

function scrollToCanvas() {

	$(function() {
		$('html, body').animate({
			scrollTop : $('#canvas').offset().top
		});
	});
}

/*
 * function afisMasiniNeincarcate(listMasini) {
 * 
 * $('#masini_select').empty();
 * 
 * if (listMasini.length == 0) { $('#masini_select').append($('<option>', {
 * value : 0, text : "Nu exista masini de incarcat" }));
 * 
 * $('#labelMasini').text(""); $('#sfIncarcare').hide(); }
 * 
 * if (listMasini.length > 0) {
 * 
 * if (listMasini.length == 1) $('#labelMasini') .text("Exista 1 masina ce
 * urmeaza a fi incarcata.");
 * 
 * else $('#labelMasini').text( "Exista " + listMasini.length + " masini ce
 * urmeaza a fi incarcate.");
 * 
 * $('#masini_select').append($('<option>', { value : 0, text : "Selectati o
 * masina" }));
 * 
 * for (var i = 0; i < listMasini.length; i++) { $('#masini_select').append($('<option>', {
 * value : listMasini[i].nrBorderou, text : listMasini[i].nrMasina })); }
 * 
 * $("#masini_select").val("0").change();
 *  }
 * 
 * 
 * 
 * //$('#masini_select').selectmenu("refresh", true);
 * 
 * $('#masini_select').hide();
 *  }
 */

/*
 * function onChangeMasini() {
 * 
 * $('#masini_select').on('change', function() { var documentId =
 * $("#masini_select option:selected").val();
 * 
 * if (documentId == '0') { $('#sfIncarcare').hide(); $('#camdiv').hide();
 * $('#canvas').hide(); $('#nrAuto').text(""); } else { $('#camdiv').show();
 * scrollToCamDiv();
 *  }
 * 
 * });
 *  }
 */

function onClickIncarcare() {

	$("#sfIncarcare")
			.click(
					function() {

						var image = document.getElementById("canvas")
								.toDataURL("image/png").replace("image/png",
										"image/octet-stream");

						setSfarsitIncarcare(image);

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

				var masina = $.parseJSON(JSON.stringify(data));
				nrBorderou = masina.nrBorderou;
				
				$('#nrAuto').text(masina.nrMasina);
				$.mobile.loading('hide');
				$('#sfIncarcare').show();
				scrollToBottom();

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

				$('#sfIncarcare').hide();
				$('#canvas').hide();
				$('#nrAuto').text("");

				// getMasiniNeincarcate();
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
