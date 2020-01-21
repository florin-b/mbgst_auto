var userObj;

$(document).on('pagebeforeshow', '#main_menu', function() {

});

$(document).on('pageshow', '#main_menu', function() {

	$('#listMenu').parent().css('margin-left', '10px');
	$('#listMenu').parent().css('margin-right', '10px');

	userObj = JSON.parse($('#userbean').text());

	getMainMenu();

	setInterval(getMainMenu, 10000);

});

function initMenu() {
	var menuList = JSON.parse($('#menubean').text());

	$("listMenu").empty();

	for (var i = 0; i < menuList.length; i++) {

		var text;

		if (menuList[i].navNumber > 0)

			text = '<a href=' + menuList[i].link + ' data-ajax="false">'
					+ menuList[i].text + ' <span class="ui-li-count">'
					+ menuList[i].navNumber + '</span></a>';
		else
			text = '<a href=' + menuList[i].link + ' data-ajax="false">'
					+ menuList[i].text + ' </a>';

		$("#listMenu").append($("<li>").html(text));
	}

	$('#listMenu').listview('refresh');
	$('#listMenu').trigger("create");

}

function initMenuObject(menuList) {

	$("#listMenu").empty();

	for (var i = 0; i < menuList.length; i++) {

		var text;

		if (menuList[i].navNumber > 0)

			text = '<a href=' + menuList[i].link + ' data-ajax="false">'
					+ menuList[i].text + ' <span class="ui-li-count">'
					+ menuList[i].navNumber + '</span></a>';
		else
			text = '<a href=' + menuList[i].link + ' data-ajax="false">'
					+ menuList[i].text + ' </a>';

		$("#listMenu").append($("<li>").html(text));
	}

	$('#listMenu').listview('refresh');
	$('#listMenu').trigger("create");

}

function getMainMenu() {

	$.ajax({
		type : 'GET',
		url : 'mainMenu',
		data : ({
			tipUser : userObj.tipAngajat
		}),
		beforeSend : function() {
			loading('show');
		},
		complete : function() {
			loading('hide');
		},
		success : function(data) {
			$.mobile.loading('hide');
			initMenuObject(data);

		},

		error : function(exception) {
			alert(exception);
		}

	});

}

function loading(showOrHide) {
	setTimeout(function() {
		$.mobile.loading(showOrHide);
	}, 1);
}
