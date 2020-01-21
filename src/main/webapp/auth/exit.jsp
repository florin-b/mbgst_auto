<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WebSFA Login</title>



<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

<script
	src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

</head>
<body>

	<div data-role="page" id="init" data-url="">

		<div data-role="header" data-theme="a">
			<h1>WebSFA Moldova</h1>
		</div>


		<div data-role="content" class="ui-content">




			<font color="red">${message}</font>
			<form:form id="loginForm" method="post" action="login"
				modelAttribute="login">

				<h3>Autentificare</h3>

				<form:label path="username">Utilizator</form:label>
				<form:input id="username" name="username" path="" value="androsd"/>
				<br>
				<form:label path="username">Parola</form:label>
				<form:password id="password" name="password" path="" value= "112"/>
				<br>
				<input type="submit" value="Login" />
				<br>
				<div id='logStatus'>${infoMsg}</div>
			</form:form>
		</div>
	</div>
</body>










</html>