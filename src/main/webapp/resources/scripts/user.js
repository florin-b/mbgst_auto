var userObj;

$(document).on('pageshow', '#user', function() {

	userObj = JSON.parse($('#userbean').text());

	populateUserData();

});

$(document).on('pagecreate', '#user', function() {

});

function populateUserData() {

	var dateGenTable = '#userdef-table';
	$('#userdef-table tbody').remove();

	var row = $('<tr></tr>').appendTo(dateGenTable);
	$('<td></td>').attr('style', 'width:20%').css("font-weight", "bold").text(
			'Nume').appendTo(row);
	$('<td></td>').text(userObj.nume).appendTo(row);

	row = $('<tr></tr>').appendTo(dateGenTable);
	$('<td></td>').attr('style', 'width:20%').css("font-weight", "bold").text('Filiala').appendTo(row);
	$('<td></td>').text(userObj.filiala).appendTo(row);

	row = $('<tr></tr>').appendTo(dateGenTable);
	$('<td></td>').attr('style', 'width:20%').css("font-weight", "bold").text('Cod angajat').appendTo(row);
	$('<td></td>').text(userObj.codPers).appendTo(row);

	row = $('<tr></tr>').appendTo(dateGenTable);
	$('<td></td>').attr('style', 'width:20%').css("font-weight", "bold").text('Departament').appendTo(row);
	$('<td></td>').text(userObj.codDepart).appendTo(row);

	row = $('<tr></tr>').appendTo(dateGenTable);
	$('<td></td>').attr('style', 'width:20%').css("font-weight", "bold").text('Tip angajat').appendTo(row);
	$('<td></td>').text(getTipAngajat(userObj.tipAngajat)).appendTo(row);

}