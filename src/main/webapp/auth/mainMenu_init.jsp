<!DOCTYPE html>
<html>
<head>
<title>WebSFA</title>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

<script
	src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>



</head>
<body>
	<div data-role="page" id="main-page" data-theme="a"
		data-url="auth/mainMenu.jsp">


		<div data-role="panel" data-display="overlay" id="left-panel1"
			data-position="left" data-theme="a">
			<ul data-role="listview">
				<jsp:include page="navbar.jsp">
					<jsp:param name="tipuser" value="${user.tipAngajat}" />
					<jsp:param name="numeuser" value="${user.nume}" />
				</jsp:include>
			</ul>
			<script type="text/javascript">
				$(document).on('pageshow', function() {
					$('#left-panel1').panel('open');
				});
			</script>
		</div>
		<div data-role="header" data-theme="a">
			<h1></h1>
			<a href="#left-panel1" data-theme="a" data-icon="arrow-r"
				data-iconpos="notext" data-shadow="false" data-iconshadow="false"
				class="ui-icon-nodisc">Meniu</a>

		</div>

	</div>

</body>
</html>
