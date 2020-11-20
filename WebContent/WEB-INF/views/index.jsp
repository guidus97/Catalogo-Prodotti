<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
	<jsp:include page="fragment/menu.jsp"></jsp:include>
	<div class="jumbotron">
		<div class="container text-center">
			<h1>Home</h1>
		</div>
		<div>
			<h6 ${ warning ? '' : 'hidden' } class="alert alert-warning"
				role="alert">${ message }</h6>
		</div>
	</div>
</body>
</html>