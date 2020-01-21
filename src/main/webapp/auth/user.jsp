<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Preturi</title>



<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

<script
	src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>


<script src="<c:url value="/resources/scripts/user.js" />"></script>
<script src="<c:url value="/resources/scripts/common_scripts.js" />"></script>


<style>
#listDiv {
	height: 450px;
	overflow: scroll;
}

#pretTable {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

#pretTable td {
	padding: 2px;
}
</style>

</head>


<body>


	<div data-role="page" id="user" data-theme="a" data-url="">



		<div data-role="header" data-theme="a">
			<h1>Utilizator</h1>
			<a href="main" data-ajax="false" data-theme="d" data-icon="arrow-l"
				data-iconpos="notext" data-shadow="true" data-iconshadow="true"
				class="ui-icon-nodisc"></a>
		</div>





		<div data-role="content" class="ui-content">

			<br>
			<table data-role="table" id="userdef-table" data-mode="reflow"
				class="ui-responsive table-stroke">


			</table>



		</div>
	</div>

	<div id="userbean" style="visibility: hidden">${userjson}</div>

</body>
</html>