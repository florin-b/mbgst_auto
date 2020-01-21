<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Incarcare masini</title>



<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

<script
	src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

<script
	src="https://requirejs.org/docs/release/2.3.5/minified/require.js"></script>



<link rel="stylesheet" href="resources/css/afiseaza_comanda.css">

<script src="<c:url value="/resources/scripts/incarcare_masini.js" />"></script>



<style>
#listDiv {
	height: 400px;
	overflow: scroll;
}

#datelivrareTable, #dategeneraleTable tbody td, th {
	color: #68838B;
	font-weight: normal;
}

#comenziTable tbody td {
	color: #473C8B;
	font-weight: normal;
}

#articoleTable, #comenziTable {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

#articoleTable td, #comenziTable  td {
	padding: 2px;
}
</style>

<style>
video {
	border: 1px solid #ccc;
	display: block;
	margin: 0 0 20px 0;
}

#canvas {
	margin-top: 20px;
	border: 1px solid #ccc;
	display: block;
}
</style>

</head>


<body>

	<div data-role="page" id="incarc_masini" data-theme="a" data-url="">

		<div data-role="header" data-theme="a">
			<h1>Incarcare masini</h1>
			<a href="main" data-ajax="false" id="afisMainLink" data-theme="d"
				data-icon="arrow-l" data-iconpos="notext" data-shadow="true"
				data-iconshadow="true" class="ui-icon-nodisc"></a>
		</div>


		<div data-role="content" class="ui-content">

			<br> <br>

			<table style="width: 100%;">
				<tr>
					<td style="width: 70%; display: none"><h3 id='labelMasini'></h3></td>
					<td><a href="#" id="refreshList" class="ui-btn ui-corner-all"
						style="background: #DCDDFD; display: none">Actualizeaza</a></td>
				</tr>
			</table>

			<br> <br>



			<div id="camdiv" align="center">
				<video id="video" width="768" height="576" autoplay></video>
				<br>
				<button id="foto" style="background: #99FFD3;">Foto</button>
				<br>
				<canvas id="canvas" width="768" height="576"></canvas>



				<h2 id="nrAuto"></h2>
			</div>

			<br>

			<div id="pageBottom"></div>

		</div>
	</div>


	<div data-role="dialog" id="dialogIncarcare">
		<div data-role="header">
			<h1 id="tipAlertA"></h1>
		</div>
		<div data-role="content">
			<div id="textAlertA"></div>
		</div>
	</div>

	<div id="userbean" style="visibility: hidden">${userjson}</div>




	<script
		src="<c:url value="/resources/helper_scritps/helper_livrare.js" />"></script>


	<script>
		var canvas = document.getElementById('canvas');
		var context = canvas.getContext('2d');
		var video = document.getElementById('video');

		if (navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
			navigator.mediaDevices.getUserMedia({
				video : {
					facingMode : "environment",

				}
			}).then(function(stream) {
				window.stream = stream;
				video.srcObject = stream;

			});

		}

		document.getElementById("foto").addEventListener("click", function() {
			context.drawImage(video, 0, 0, 768, 576);
			showSaveButton();

		});
	</script>


</body>
</html>