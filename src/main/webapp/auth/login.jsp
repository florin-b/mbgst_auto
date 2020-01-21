<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page language="java" contentType="text/html; " pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MobiGEST Login</title>



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
			<h1>MobiGEST</h1>
		</div>

		<div data-role="content" class="ui-content">

			<font color="red">${message}</font>
			<form:form id="loginForm" method="post" action="login"
				modelAttribute="login">

				<h3>Autentificare</h3>


				<form:label path="username">Utilizator</form:label>
				<form:input id="username" name="username" path="" />
				<br>
				<form:label path="username">Parola</form:label>
				<form:password id="password" name="password" path="" />
				<br>
				<input type="submit" value="Login" />
				<br>
				<div id='logStatus'>${infoMsg}</div>

				<div id="redirectUrl">${redirectUrl}</div>

				<script type="text/javascript">
					var redirect = '${redirectUrl}';

					if (redirect) {
						window.location.replace(window.location.origin
								+ window.location.pathname.substr(0,
										window.location.pathname
												.lastIndexOf('/')) + redirect);
					}
				</script>

			</form:form>
		</div>
	</div>




</body>








</html>