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

<title>Insert title here</title>

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

	<div data-role="page" id="capture" data-url="">


		<video id="video" width="640" height="480" autoplay></video>
		<button id="snap">Snap Photo</button>
		<canvas id="canvas" width="640" height="480"></canvas>

		<br> <br>
		<button id="save">Save</button>
		<br> <br> <br>


	</div>

	<script>
		
	</script>

<script src="<c:url value="/resources/scripts/capture.js" />"></script>

</body>
</html>